package me.stun.chart;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.BasicStroke;
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

public class CupsChart {

	public static JFrame frame = new JFrame();
	public static String displayedCard = "Archer Queen";
	public static XYSeries density;

	public static ChartPanel getLineChart(String chartTitle, String playerTag, String[][] matches) {
		JFreeChart lineChart = ChartFactory.createXYLineChart(chartTitle, "Cups", "Percentage", createCupsDataset(matches),
				PlotOrientation.VERTICAL, true, true, false);

		XYSplineRenderer splineRenderer = new XYSplineRenderer();
		splineRenderer.setBaseStroke(new BasicStroke(3.0f));
		((AbstractRenderer) splineRenderer).setAutoPopulateSeriesStroke(false);
		splineRenderer.setSeriesShapesVisible(0, false);
		splineRenderer.setSeriesShapesVisible(1, false);
		splineRenderer.setSeriesShapesVisible(2, false);
		splineRenderer.setBaseLegendShape(new Rectangle(150, 10));
		splineRenderer.setLegendLine(new Rectangle(90,1));

		lineChart.setBackgroundPaint(me.stun.gui.Window.menudark);
		lineChart.getLegend().setBackgroundPaint(me.stun.gui.Window.menudark);
		lineChart.getLegend().setFrame(BlockBorder.NONE);
		lineChart.getLegend().setItemPaint(Color.WHITE);
		XYPlot plot = lineChart.getXYPlot();
		plot.setRenderer(splineRenderer);

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

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(920, 400));
		chartPanel.setBackground(me.stun.gui.Window.menudark);
		return chartPanel;
	}

	public static XYDataset createCupsDataset(String[][] matches) {

		// -------------------------------------------------------------------------------------

		int counter = 0;

		for (int i = 0; i < matches.length; i++) {

			for (int j = 0; j < 8; j++) {
				if (matches[i][j].equalsIgnoreCase(displayedCard)) {
					counter++;
					j = 8;
				}
			}

		}

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

		LinkedList<Integer> cupValues = new LinkedList<Integer>();
		for (int i = 5000; i < 7001; i = i + 100) {
			cupValues.add(i);
		}

		int index = 0;

		for (int i = 0; i < me.stun.data.DeckData.cards.length; i++) {

			if (displayedCard.equals(me.stun.data.DeckData.cards[i])) {
				index = i;
			}

		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series = new XYSeries(DeckData.cards[index]);
		XYSeries average = new XYSeries("average");
		density = new XYSeries("density");

		int stepSize = cupValues.size() / 21;
		int steps = cupValues.size() / 10;

		for (int i = 0; i < cupValues.size(); i++) {

			// for every timestamp

			// for every card
			int cardCount = 0;
			int matchcounter = 0;

			for (int k = 0; k < matches.length; k++) {

				// for every match

				if (Integer.parseInt(matches[k][10]) > cupValues.get(i)
						&& Integer.parseInt(matches[k][10]) < cupValues.get(i) + 100
						|| Integer.parseInt(matches[k][10]) > cupValues.get(cupValues.size() - 1)
								&& i == cupValues.size() - 1
						|| Integer.parseInt(matches[k][10]) < 5000 && i == 0) {

					// if cups are correct

					for (int l = 0; l < 8; l++) {

						if (matches[k][l].equals(DeckData.cards[index])) {

							cardCount++;

						}

					}
					
					matchcounter++;

				}

			}

			if (cupValues.get(i) != null) {

				float value = (float) cardCount / matchcounter * 100;
				series.add((double) cupValues.get(i), value);
				
				float densityValue = (float) matchcounter / matches.length * 100;
				density.add((double) cupValues.get(i), densityValue);

			}

			if (i % stepSize == 0) {
				me.stun.startup.StartupImage.plotProgressbar
						.setValue(me.stun.startup.StartupImage.plotProgressbar.getValue() + 1);
			}
			if (i % steps == 0) {

				me.stun.startup.StartupImage.progressbar
						.setValue(me.stun.startup.StartupImage.progressbar.getValue() + 1);

			}

			me.stun.thread.ProgressbarWorker.progress = me.stun.thread.ProgressbarWorker.progress + 3;

		}

		me.stun.startup.StartupImage.plotProgressbar.setVisible(false);
		me.stun.thread.ProgressbarWorker.running = false;

		float averageFloat = me.stun.data.DataProcessor.getPercentage(matches, displayedCard);
		average.add((double) cupValues.get(0), averageFloat);
		average.add((double) cupValues.get(cupValues.size() - 1), averageFloat);

		dataset.addSeries(average);
		dataset.addSeries(series);
		dataset.addSeries(density);
		return dataset;

	}

}
