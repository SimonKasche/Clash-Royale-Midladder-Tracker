package me.stun.thread;

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
		
		me.stun.gui.Window.timeChartContainer.add(me.stun.chart.TimeChart.buildPlot(matches));
		me.stun.gui.Window.cupChartContainer.add(me.stun.chart.CupsChart.getLineChart("", "", matches));		
		
		me.stun.gui.Window.cupChartContainer.revalidate();
		me.stun.gui.Window.cupChartContainer.repaint();
		me.stun.gui.Window.timeChartContainer.revalidate();
		me.stun.gui.Window.timeChartContainer.repaint();
		
	}
	
}
