package me.stun.chart;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Color;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;

import me.stun.gui.Window;

@SuppressWarnings("serial")
public class WinConditionDial {
	
	public static JFrame frame = new JFrame();
	
	public static void launch(String[][] matches) {

		EventQueue.invokeLater(() -> {
			new WinConditionDial(matches);

		});

	}

	public WinConditionDial(String[][] matches) {
		
		frame.setLocation(900, 250);	
		frame.setTitle("Average Win Conditions used per Deck");
		
        frame.add(buildDialPlot(matches, 0, 3, 1));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }

	private static DefaultValueDataset dataset;

	public static  ChartPanel buildDialPlot(String[][] matches, int minimumValue, int maximumValue, int majorTickGap) {
		
		dataset = new DefaultValueDataset(me.stun.data.DeckData.getWinConditionAverage(matches));
		
		DialPlot plot = new DialPlot(dataset);
		StandardDialFrame dialFrame = new StandardDialFrame();
		DialPointer.Pointer pointer = new DialPointer.Pointer();
		StandardDialScale scale = new StandardDialScale(minimumValue, maximumValue, -120, -300, majorTickGap,
				majorTickGap - 1);
		
		dialFrame.setForegroundPaint(Color.WHITE);
		scale.setTickRadius(0.88);
		scale.setTickLabelOffset(0.20);
		scale.setMajorTickPaint(Color.WHITE);
		pointer.setFillPaint(Color.WHITE);
		pointer.setOutlinePaint(Color.BLACK);
		
		plot.setDialFrame(dialFrame);
		plot.addLayer(pointer);
		plot.addScale(0, scale);
		
		DialBackground background = new DialBackground(Window.menudark);
		plot.setBackground(background);
		
		JFreeChart freeChart = new JFreeChart(plot);
		freeChart.setBackgroundPaint(Window.menudark);
		
		ChartPanel panel = new ChartPanel(freeChart) {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(500, 500);
			}
		};
		
		return panel;
	}
}
