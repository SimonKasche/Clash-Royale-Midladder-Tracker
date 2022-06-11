package me.stun.thread;

public class UpdateWorker extends Thread{
	
	public void run() {		
		
		me.stun.gui.Window.timeChartContainer.removeAll();
		me.stun.gui.Window.timeChartContainer.revalidate();
		me.stun.gui.Window.timeChartContainer.repaint();
		
		me.stun.gui.Window.cupChartContainer.removeAll();
		me.stun.gui.Window.cupChartContainer.revalidate();
		me.stun.gui.Window.cupChartContainer.repaint();
		
		me.stun.gui.Window.timeChartContainer.add(me.stun.chart.TimeChart.buildPlot());
		me.stun.gui.Window.cupChartContainer.add(me.stun.chart.CupsChart.getLineChart("", "", me.stun.data.DeckData.totalMatches));		
		
		me.stun.gui.Window.cupChartContainer.revalidate();
		me.stun.gui.Window.cupChartContainer.repaint();
		me.stun.gui.Window.timeChartContainer.revalidate();
		me.stun.gui.Window.timeChartContainer.repaint();
		
	}
	
}
