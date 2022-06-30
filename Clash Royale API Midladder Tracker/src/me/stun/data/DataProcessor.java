package me.stun.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import me.stun.io.ArrayBuilder;

public class DataProcessor {
	
	public static String[] playerTags;
	public static int playerTagCounter = 0;
	
	public static String[][] getMatchesPerPlayer(String[][] totalMatches, String playerID) {

		LinkedList<String[]> output = new LinkedList<String[]>();

		for (int i = 0; i < totalMatches.length; i++) {

			if (totalMatches[i][9].equalsIgnoreCase(playerID)) {
				output.add(totalMatches[i]);
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

	public static void addNewMatches(LinkedList<String[]> matches, String[][] totalMatches) {

		boolean found = false;
		int counter = 0;

		String[][] newMatches = new String[totalMatches.length + matches.size()][totalMatches[0].length];
		for (int i = 0; i < totalMatches.length; i++) {

			newMatches[i] = totalMatches[i];

		}
		int index = totalMatches.length;

		if (totalMatches.length <= 1 || totalMatches[0].length <= 1) {
			System.out.println("unable to add matches matches.txt is empty");
		} else {

			for (int i = 0; i < matches.size(); i++) {

				for (int j = 0; j < newMatches.length; j++) {

					if (newMatches[j][8] != null) {
						if (matches.get(i)[8].equalsIgnoreCase(totalMatches[j][8])) {
							found = true;
							j = totalMatches.length;
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
		
		totalMatches = removeNullValues(newMatches);

		me.stun.startup.Console.TextArea.append("added '" + counter + "' new matches\n");
		me.stun.startup.Console.TextArea.append("total matches recorded: '" + totalMatches.length + "'\n");

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
	
}
