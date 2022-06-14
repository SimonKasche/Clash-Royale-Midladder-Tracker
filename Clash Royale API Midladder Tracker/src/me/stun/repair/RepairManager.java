package me.stun.repair;

import java.io.IOException;

import me.stun.startup.Console;

public class RepairManager extends Thread {

	@SuppressWarnings("deprecation")
	public void run() {

		MainProcess.matches = me.stun.data.DeckData.totalMatches;
		MainProcess.repair();
		try {
			MainProcess.removeDuplicates();
			Console.printLine("successfully removed " + MainProcess.duplicateCount + " duplicateMatches");
			Console.printLine("\nplease reload to apply changes");
			me.stun.gui.Window.repairWorker.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
