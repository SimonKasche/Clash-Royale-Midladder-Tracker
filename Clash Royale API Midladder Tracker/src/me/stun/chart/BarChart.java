package me.stun.chart;

import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import me.stun.data.DeckData;

public class BarChart {
	
	public static JFrame frame = new JFrame();
	
	public static void barChart(String applicationTitle, String chartTitle, String playerTag, String[][] matches) {
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Legend", "Percentage", createDataset(playerTag, matches),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1920, 1080));
		frame.setContentPane(chartPanel);
	}
	
	public static ChartPanel getBarChart(String chartTitle, String playerTag, String[][] matches) {
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "", "Percentage", createDataset(playerTag, matches),
				PlotOrientation.VERTICAL, false, true, false);
		
		CategoryPlot categoryPlot = barChart.getCategoryPlot();
		BarRenderer categoryItemRenderer = (BarRenderer) categoryPlot.getRenderer();
		categoryItemRenderer.setShadowVisible(false);
		
		barChart.setBackgroundPaint(me.stun.gui.Window.menudark);
		barChart.getPlot().setBackgroundPaint(me.stun.gui.Window.dark);
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(905, 400));
		chartPanel.setBackground(me.stun.gui.Window.menudark);
		return chartPanel;
	}

	private static CategoryDataset createDataset(String playerTag, String[][] matches) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < DeckData.cardCounter.size(); i++) {

			for (Map.Entry<String, Integer> entry : DeckData.sortedCardCounter.entrySet()) {
				if (entry.getValue() != 0) {
					dataset.addValue((float) entry.getValue() / matches.length * 100, entry.getKey(), "");
				}
			}

		}

		return dataset;
	}

	public static void launch(String[][] matches, String playerTag) {
		DeckData.getCardUsage(matches);
		barChart("Card Usage", "Card Usage", playerTag, matches);
		
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
	}
	
}
