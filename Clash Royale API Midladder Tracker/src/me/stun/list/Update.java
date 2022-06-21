package me.stun.list;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

import me.stun.startup.Console;

public class Update {

	private static final DecimalFormat df = new DecimalFormat("0.000");
	public static boolean wait = false;

	public static void getPlayerData() throws IOException, ParseException {

		IO.playerTags = readFile("src/UpdaterPlayerTag.txt");
		LinkedList<String> playerTags = getPlayerTags();
		IO.getUsedTags();

		for (IO.playerTagCounter = 0; IO.playerTagCounter < playerTags.size() - 1; IO.playerTagCounter++) {

			// read battle history from clash royale api
			try {
				Long connectionStart = System.nanoTime();
				me.stun.net.Connection.getBattleHistory(playerTags.get(IO.playerTagCounter));
				Long connectionEnd = System.nanoTime();

				Long start = System.nanoTime();
				LinkedList<String[]> matches = me.stun.io.ParseJsonFile.readBattleHistory();

				IO.addNewMatches(matches);
				Console.print("\t" + IO.playerTagCounter + "/" + playerTags.size() + "\t addedmatches.size() = "
						+ IO.addedMatches.size());
				Console.print("\tcalc = " + df.format((System.nanoTime() - start) / 1e+9) + "s");
				Console.print("\tresponse = " + df.format((connectionEnd - connectionStart) / 1e+9) + "s");

				if (wait == true) {

					synchronized (me.stun.gui.Window.listUpdater) {
						me.stun.gui.Window.listUpdater.wait();
					}

				}if(me.stun.list.ListUpdater.stop == true) {
					
					//stop download
					me.stun.gui.Window.stopDownload.setText("Stopping..");
					IO.playerTagCounter = playerTags.size();
					
				}

			} catch (Exception e) {
				Console.print("\trequest failed: ");
				e.printStackTrace();
			}

		}

	}

	public static LinkedList<String> getPlayerTags() throws IOException, ParseException {

		LinkedList<String> output = new LinkedList<String>();
		me.stun.net.Connection.getPlayerClanTag(IO.playerTags[0]);
		String clanTag = me.stun.io.ParseJsonFile.readClanTag();

		me.stun.net.Connection.getClanPlayers(clanTag);
		LinkedList<String> firstTags = me.stun.io.ParseJsonFile.readClanPlayers();

		for (int i = 0; i < firstTags.size(); i++) {

			try {

				me.stun.net.Connection.getBattleHistory(firstTags.get(i));
				LinkedList<String> battleTags = me.stun.io.ParseJsonFile.readBattleHistoryTags();

				for (int k = 0; k < battleTags.size(); k++) {
					output.add(battleTags.get(k));
				}

			} catch (Exception e) {
				Console.printLine("parsing failed");
			}
			Console.print("getting players " + i + "/50\t");
			Console.printLine("playerTags.size() = " + output.size());
		}

		return output;

	}

	public static String getApiKey() throws IOException {

		FileReader fr = new FileReader("src/ApiKey.txt");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

		return br.readLine();

	}

	public static String getPlayerTag() throws IOException {

		FileReader fr = new FileReader("src/PlayerTag.txt");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

		return br.readLine();

	}

	public static String[] readFile(String path) {
		try {
			FileReader f = new FileReader(path);
			BufferedReader b = new BufferedReader(f);

			String[] output = new String[IO.countLines(path)];

			for (int i = 0; i < IO.countLines(path); i++) {
				String line = b.readLine();
				if (line == null) {
					output[i] = "";
				} else {
					output[i] = line;
				}
			}

			b.close();
			f.close();

			return output;

		} catch (Exception e) {
			Console.printLine("File '" + path + "' could not be found");
			return null;
		}

	}

}
