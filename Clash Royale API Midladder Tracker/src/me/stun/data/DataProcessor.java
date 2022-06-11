package me.stun.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

import me.stun.io.ArrayBuilder;
import me.stun.net.ConnectionResources;

public class DataProcessor {
	
	public static String[] playerTags;
	public static int playerTagCounter = 0;
	
	public static String[][] getMatchesPerPlayer(String playerID) {

		LinkedList<String[]> output = new LinkedList<String[]>();

		for (int i = 0; i < DeckData.totalMatches.length; i++) {

			if (DeckData.totalMatches[i][9].equalsIgnoreCase(playerID)) {
				output.add(DeckData.totalMatches[i]);
			}

		}

		return me.stun.data.DeckData.matchesToArray(output);
	}

	public static float getPercentage(String[][] matches, String key) {

		float total = 0;
		float counter = 0;
		boolean found = false;

		for (int i = 0; i < matches.length; i++) {
			for (int j = 0; j < matches[i].length; j++) {
				if (matches[i][j].equalsIgnoreCase(key)) {
					found = true;
				}
			}
			if (found == true) {
				counter++;
			}
			total++;
			found = false;
		}

		return (float) counter / total * 100;

	}

	public static LinkedList<String[]> readPreviousMatches() throws IOException {

		LinkedList<String[]> readMatches = new LinkedList<String[]>();
		BufferedReader br = new BufferedReader(new FileReader("matches.txt"));

		String[] deck = new String[9];
		int lines = ArrayBuilder.countLines("matches.txt");

		for (int i = 0; i < lines; i++) {
			String line = br.readLine();
			System.out.println("reading match: " + i);
			if (line == null) {
				// idfk
			} else {
				deck = line.split(" , ");
				if (deck != null && deck.length > 9)
					readMatches.add(deck);
			}
		}

		br.close();
		return readMatches;

	}

	public static void addNewMatches(LinkedList<String[]> matches) {

		boolean found = false;
		int counter = 0;

		String[][] newMatches = new String[DeckData.totalMatches.length + matches.size()][DeckData.totalMatches[0].length];
		for (int i = 0; i < DeckData.totalMatches.length; i++) {

			newMatches[i] = DeckData.totalMatches[i];

		}
		int index = DeckData.totalMatches.length;

		if (DeckData.totalMatches.length <= 1 || DeckData.totalMatches[0].length <= 1) {
			System.out.println("unable to add matches matches.txt is empty");
		} else {

			for (int i = 0; i < matches.size(); i++) {

				for (int j = 0; j < newMatches.length; j++) {

					if (newMatches[j][8] != null) {
						if (matches.get(i)[8].equalsIgnoreCase(DeckData.totalMatches[j][8])) {
							found = true;
							j = DeckData.totalMatches.length;
						}
					}else {
						j = newMatches.length;
					}

				}

				if (found == false) {
					newMatches[index] = matches.get(i);
					counter++;
					index ++;
				} else {
					found = false;
				}

			}
		}
		
		DeckData.totalMatches = removeNullValues(newMatches);

		me.stun.startup.Console.TextArea.append("added '" + counter + "' new matches\n");
		me.stun.startup.Console.TextArea.append("total matches recorded: '" + DeckData.totalMatches.length + "'\n");

	}
	
	public static String[][] removeNullValues(String[][] input){
		int index = 0;
		
		for(int i = 0;i<input.length;i++) {
			
			if(input[i][0] == null) {
				index = i;
				i = input.length;
			}
			
		}
		
		String[][] output = new String[index][input[0].length];
		for(int i = 0;i<output.length;i++) {
			
			output[i] = input[i];
			
		}
		
		return output;
		
	}

	public static boolean stopDownloading = true;

	public static void downloadRandomData() throws IOException, ParseException {

		playerTags = ConnectionResources.readFile("src/PlayerTag.txt");
		me.stun.startup.Console.TextArea.append("establishing connection with api.clashroyale.com..\n");
		stopDownloading = false;
		while (stopDownloading == false) {
			for (playerTagCounter = 0; playerTagCounter < playerTags.length - 1; playerTagCounter++) {

				StringBuilder sb = new StringBuilder(playerTags[playerTagCounter]);
				sb.replace(0, 1, "%23");

				// read battle history from clash royale api
				me.stun.net.Connection.getBattleHistory(sb.toString());

				LinkedList<String[]> matches = me.stun.io.ParseJsonFile.readBattleHistory();
				addNewMatches(matches);

			}

			for (int i = playerTags.length - 1; i >= 0; i--) {

				playerTags[i] = DeckData.totalMatches[DeckData.totalMatches.length - i - 1][8];

			}
		}

	}
	
	
}
