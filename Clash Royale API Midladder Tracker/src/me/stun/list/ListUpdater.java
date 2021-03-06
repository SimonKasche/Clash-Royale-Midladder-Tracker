package me.stun.list;

import me.stun.startup.Console;

public class ListUpdater extends Thread {
	
	public static boolean running = false;
	public static boolean stop = false;
	
	public String[][] totalMatches;
	
	public ListUpdater(String[][] matches) {
		
		this.totalMatches = matches;
		
	}

	@SuppressWarnings("deprecation")
	public void run() {
		
		running = true;
		me.stun.gui.Window.downloadProgressBar.setVisible(true);
		me.stun.gui.Window.downloadProgressBar.setValue(0);
		
		try {
			Long start = System.nanoTime();

			Console.printLine("reading match data..");
			Update.getPlayerData(totalMatches);

			String[][] matches = IO.addMatchesToArray(IO.addedMatches, totalMatches);
			Console.print("\nsaving matches to matches.txt.. ");
			IO.writeArray(matches);

			Console.printLine("\nsuccessfully added " + IO.addedMatches.size() + " matches to dataset, time = "
					+ (System.nanoTime() - start) / 6e+10 + "min\n");
			
			me.stun.gui.Window.downloadOutput.setText("reload to apply changes");
			
			running = false;
			me.stun.gui.Window.stopDownload.setVisible(false);
			me.stun.gui.Window.pauseDownload.setVisible(false);
			me.stun.gui.Window.downloadMoreData.setVisible(true);
			me.stun.gui.Window.stopDownload.setText("Stop Download");
			me.stun.gui.Window.downloadProgressBar.setVisible(true);
			me.stun.gui.Window.listUpdater.stop();
		} catch (Exception e) {
			Console.printLine(e.toString());
			e.printStackTrace();
		}

	}

}
