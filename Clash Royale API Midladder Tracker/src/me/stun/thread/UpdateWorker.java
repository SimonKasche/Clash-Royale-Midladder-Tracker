package me.stun.thread;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.chart.renderer.xy.XYSplineRenderer;

import me.stun.gui.Window;

public class UpdateWorker extends Thread{
	
	public String[][] matches;
	
	public UpdateWorker(String[][] totalMatches) {
		
		this.matches = totalMatches;
		
	}
	
	public void run() {		
		
		me.stun.gui.Window.timeChartContainer.removeAll();
		me.stun.gui.Window.timeChartContainer.revalidate();
		me.stun.gui.Window.timeChartContainer.repaint();
		
		me.stun.gui.Window.cupChartContainer.removeAll();
		me.stun.gui.Window.cupChartContainer.revalidate();
		me.stun.gui.Window.cupChartContainer.repaint();
		
		ChartPanel timePanel = me.stun.chart.TimeChart.buildPlot(matches);
		ChartPanel cupsPanel = me.stun.chart.CupsChart.getLineChart("", "", matches);
		addCupsActionListener(cupsPanel);
		addTimeActionListener(timePanel);
		me.stun.gui.Window.timeChartContainer.add(timePanel);
		me.stun.gui.Window.cupChartContainer.add(cupsPanel);		
		
		me.stun.gui.Window.cupChartContainer.revalidate();
		me.stun.gui.Window.cupChartContainer.repaint();
		me.stun.gui.Window.timeChartContainer.revalidate();
		me.stun.gui.Window.timeChartContainer.repaint();
		
	}
	
	public static void addCupsActionListener(ChartPanel panel) {
		
		panel.addChartMouseListener(new ChartMouseListener() {

			@Override
			public void chartMouseClicked(ChartMouseEvent event) {

				ChartEntity entity = event.getEntity();
				if (entity instanceof LegendItemEntity) {

					LegendItemEntity legendEntity = (LegendItemEntity) entity;
					XYSplineRenderer renderer = (XYSplineRenderer) event.getChart().getXYPlot().getRenderer();
					String rect = legendEntity.getShapeCoords();
					String[] coords = rect.split(",");
					int x = Integer.parseInt(coords[0]);

					if (x > 100 && x < 200) {

						if (renderer.getSeriesPaint(0) == Window.menudark)
							renderer.setSeriesPaint(0, me.stun.chart.CupsChart.averagePaint);
						else
							renderer.setSeriesPaint(0, Window.menudark);

					} else if (x > 300 && x < 400) {

						if (renderer.getSeriesPaint(1) == Window.menudark)
							renderer.setSeriesPaint(1, me.stun.chart.CupsChart.valuePaint);
						else
							renderer.setSeriesPaint(1, Window.menudark);

					} else if (x > 500 && x < 600) {

						if (renderer.getSeriesPaint(2) == Window.menudark)
							renderer.setSeriesPaint(2, me.stun.chart.CupsChart.densityPaint);
						else
							renderer.setSeriesPaint(2, Window.menudark);

					}
					
					System.out.println("instance");

				}
				System.out.println("lol");

			}

			@Override
			public void chartMouseMoved(ChartMouseEvent event) {

				// nothing lol

			}

		});
		
	}
	
	public static void addTimeActionListener(ChartPanel panel) {
		
		panel.addChartMouseListener(new ChartMouseListener() {

			@Override
			public void chartMouseClicked(ChartMouseEvent event) {

				ChartEntity entity = event.getEntity();
				if (entity instanceof LegendItemEntity) {

					LegendItemEntity legendEntity = (LegendItemEntity) entity;
					XYSplineRenderer renderer = (XYSplineRenderer) event.getChart().getXYPlot().getRenderer();
					String rect = legendEntity.getShapeCoords();
					String[] coords = rect.split(",");
					int x = Integer.parseInt(coords[0]);

					if (x > 100 && x < 200) {

						if (renderer.getSeriesPaint(0) == Window.menudark)
							renderer.setSeriesPaint(0, me.stun.chart.TimeChart.smoothPaint);
						else
							renderer.setSeriesPaint(0, Window.menudark);

					} else if (x > 300 && x < 400) {

						if (renderer.getSeriesPaint(1) == Window.menudark)
							renderer.setSeriesPaint(1, me.stun.chart.TimeChart.averagePaint);
						else
							renderer.setSeriesPaint(1, Window.menudark);

					} else if (x > 500 && x < 600) {

						if (renderer.getSeriesPaint(2) == Window.menudark)
							renderer.setSeriesPaint(2, me.stun.chart.TimeChart.valuePaint);
						else
							renderer.setSeriesPaint(2, Window.menudark);

					}

				}

			}

			@Override
			public void chartMouseMoved(ChartMouseEvent event) {

				// nothing lol

			}

		});
		
	}
	
}
