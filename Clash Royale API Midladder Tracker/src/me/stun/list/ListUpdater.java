package me.stun.list;

import me.stun.startup.Console;

public class ListUpdater extends Thread {
	
	public static boolean running = false;

	@SuppressWarnings("deprecation")
	public void run() {
		
		running = true;

		try {
			Long start = System.nanoTime();

			Console.printLine("reading match data..");
			ConnectionRessources.getPlayerData();

			String[][] matches = IO.addMatchesToArray(IO.addedMatches, me.stun.data.DeckData.totalMatches);
			Console.print("\nsaving matches to matches.txt.. ");
			IO.writeArray(matches);

			Console.printLine("\nsuccessfully added " + IO.addedMatches.size() + " matches to dataset, time = "
					+ (System.nanoTime() - start) / 6e+10 + "min\n");
			// System.out.println(me.Stun.IO.General.counter2);
			
			me.stun.gui.Window.downloadOutput.setText("reload to apply changes");
			running = false;
			me.stun.gui.Window.listUpdater.stop();
		} catch (Exception e) {
			Console.printLine(e.toString());
			e.printStackTrace();
		}

	}

}
