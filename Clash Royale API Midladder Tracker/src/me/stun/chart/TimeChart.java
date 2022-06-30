
package me.stun.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import me.stun.data.DeckData;

public class TimeChart {

	public static JFrame frame = new JFrame();
	public static String displayedCard = "Archer Queen";

	private static XYDataset dataset;
	public static boolean build = false;

	public static ChartPanel buildPlot(String[][] matches) {

		dataset = createTimeDataset(matches);

		JFreeChart chart = ChartFactory.createXYLineChart("", "time", "usage", dataset, PlotOrientation.VERTICAL, true,
				true, false);

		XYSplineRenderer splineRenderer = new XYSplineRenderer();
		splineRenderer.setBaseStroke(new BasicStroke(3.0f));
		((AbstractRenderer) splineRenderer).setAutoPopulateSeriesStroke(false);
		splineRenderer.setSeriesShapesVisible(0, false);
		splineRenderer.setSeriesShapesVisible(1, false);
		splineRenderer.setSeriesShapesVisible(2, false);
		splineRenderer.setPrecision(1);
		splineRenderer.setBaseLegendShape(new Rectangle(150, 10));
		splineRenderer.setLegendLine(new Rectangle(90, 1));

		chart.getXYPlot().getRangeAxis().setLowerMargin(0.1);
		chart.getXYPlot().getRangeAxis().setUpperMargin(0.25);
		chart.setBackgroundPaint(me.stun.gui.Window.menudark);
		chart.getLegend().setBackgroundPaint(me.stun.gui.Window.menudark);
		chart.getLegend().setFrame(BlockBorder.NONE);
		chart.getLegend().setItemPaint(Color.WHITE);
		XYPlot plot = chart.getXYPlot();
		plot.setRenderer(0, splineRenderer);

		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);
		plot.setBackgroundPaint(me.stun.gui.Window.menudark);
		plot.setOutlinePaint(me.stun.gui.Window.menudark);

		plot.getDomainAxis().setAxisLinePaint(Color.WHITE);
		plot.getDomainAxis().setLabelPaint(Color.WHITE);
		plot.getDomainAxis().setTickLabelPaint(Color.WHITE);
		plot.getRangeAxis().setAxisLinePaint(Color.WHITE);
		plot.getRangeAxis().setLabelPaint(Color.WHITE);
		plot.getRangeAxis().setTickLabelPaint(Color.WHITE);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(920, 400));
		chartPanel.setBackground(me.stun.gui.Window.menudark);
		return chartPanel;
	}

	public static String getTime(String[] input) {

		String temp = input[11];
		String time = "";
		for (int j = 0; j < temp.length(); j++) {

			if (temp.charAt(j) == 'T') {

				j = temp.length();

			} else {

				time = time + temp.charAt(j);

			}
		}
		StringBuilder sb = new StringBuilder(time);
		// sb.deleteCharAt(sb.length() - 1); //remove these to get a per day view
		// sb.deleteCharAt(sb.length() - 1); //remove these to get a per day view

		return sb.toString();

	}

	public static XYDataset createTimeDataset(String[][] matches) {

		int counter = 0;

		for (int i = 0; i < matches.length; i++) {

			for (int j = 0; j < 8; j++) {
				if (matches[i][j].equalsIgnoreCase(displayedCard)) {
					counter++;
					j = 8;
				}
			}

		}

		LinkedList<String> times = new LinkedList<String>();
		String[][] filteredMatches = new String[counter][matches[0].length];
		int indexCounter = 0;
		boolean found1 = false;

		for (int i = 0; i < filteredMatches.length; i++) {

			for (int j = indexCounter; j < matches.length; j++) {

				for (int k = 0; k < 8; k++) {
					if (matches[j][k].equalsIgnoreCase(displayedCard)) {
						found1 = true;
						k = 8;
					}
				}
				if (found1 == true) {

					found1 = false;
					indexCounter = j + 1;
					filteredMatches[i] = matches[j];
					j = matches.length;

				}

			}

		}

		// -------------------------------------------------------------------------------------

		boolean found = false;
		for (int i = 0; i < filteredMatches.length; i++) {

			String time = getTime(filteredMatches[i]);

			for (int j = 0; j < times.size(); j++) {

				if (times.get(j).equals(time)) {
					found = true;
				}

			}

			if (found == false) {

				times.add(time);

			} else {

				found = false;

			}

		}

		times.sort(null);

		int stepSize = (int) times.size() / 20;
		if (stepSize == 0)
			stepSize++;
		me.stun.startup.StartupImage.plotProgressbar.setValue(0);
		me.stun.startup.StartupImage.plotProgressbar.setVisible(true);
		int steps = times.size() / 10;

		int index = 0;

		for (int i = 0; i < me.stun.data.DeckData.cards.length; i++) {

			if (displayedCard.equals(me.stun.data.DeckData.cards[i])) {
				index = i;
			}

		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series = new XYSeries(DeckData.cards[index]);
		XYSeries average = new XYSeries("average");
		XYSeries smooth = new XYSeries("smooth");

		for (int i = 0; i < times.size(); i++) {

			// for every timestamp

			// for every card
			int cardCount = 0;
			int matchcounter = 0;

			for (int k = 0; k < matches.length; k++) {

				// for every match

				if (getTime(matches[k]).equals(times.get(i))) {

					// if time is correct

					for (int l = 0; l < 8; l++) {

						if (matches[k][l].equals(DeckData.cards[index])) {

							cardCount++;

						}

					}

					matchcounter++;

				}

			}

			if (times.get(i) != null) {

				float value = (float) cardCount / matchcounter * 100;
				series.add(Double.parseDouble(times.get(i)), value);
				if (i % 10 == 0 || i == times.size() - 1)
					smooth.add(Double.parseDouble(times.get(i)), value);

			}

			if (stepSize != 0) {
				if (i % stepSize == 0) {
					me.stun.startup.StartupImage.plotProgressbar
							.setValue(me.stun.startup.StartupImage.plotProgressbar.getValue() + 5);
					me.stun.thread.ProgressbarWorker.progress = me.stun.thread.ProgressbarWorker.progress + 2;
				}
			}
			if (steps != 0) {
				if (i % steps == 0) {

					me.stun.startup.StartupImage.progressbar
							.setValue(me.stun.startup.StartupImage.progressbar.getValue() + 1);

				}
			}

		}

		me.stun.startup.StartupImage.plotProgressbar.setVisible(false);
		build = true;

		float averageFloat = me.stun.data.DataProcessor.getPercentage(matches, displayedCard);
		average.add(Double.parseDouble(times.get(0)), averageFloat);
		average.add(Double.parseDouble(times.get(times.size() - 1)), averageFloat);

		dataset.addSeries(smooth);
		dataset.addSeries(average);
		dataset.addSeries(series);
		return dataset;

	}

}
