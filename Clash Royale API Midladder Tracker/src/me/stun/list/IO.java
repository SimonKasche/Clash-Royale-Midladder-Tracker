package me.stun.list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;

import me.stun.startup.Console;

public class IO {
	
	public static LinkedList<String[]> addedMatches = new LinkedList<String[]>();
	public static boolean failure = false;
	public static String[] playerTags;
	public static int playerTagCounter = 0;

	public static void saveMatches(LinkedList<String[]> matches) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter("matches.txt"));
		int matchesSize = matches.size();
		for (int i = 0; i < matchesSize; i++) {
			for (int j = 0; j < matches.get(i).length; j++) {
				if (j == matches.get(i).length - 1) {
					bw.write(matches.get(i)[j]);
				} else {
					bw.write(matches.get(i)[j] + " , ");
				}
			}
			bw.newLine();
			Console.printLine("saving: " + i + "/" + matchesSize);
		}
		bw.close();

		Console.printLine("successfully saved matches to 'matches.txt'\n");

	}

	public static void printList(LinkedList<String[]> list) {

		for (int i = 0; i < list.size(); i++) {

			for (int j = 0; j < list.get(i).length; j++) {

				Console.print(list.get(i)[j] + " | ");

			}

			Console.print("\n");

		}

	}

	public static void printArray(String[] array) {

		for (int i = 0; i < array.length; i++) {

			if (array[i] != " ")
				Console.printLine(array[i]);

		}

	}

	public static void write1DArray(String[] array) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));

		for (int i = 0; i < array.length; i++) {

			if (array[i] != null) {
				bw.write(array[i]);
				bw.newLine();
			}

		}

		bw.close();

	}

	public static void writeArray(String[][] array) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter("matches.txt"));

		for (int i = 0; i < array.length; i++) {

			for (int j = 0; j < array[i].length; j++) {

				if (array[i] != null && array[i][j] != null) {

					if (j == array[i].length - 1) {

						bw.write(array[i][j]);

					} else {

						bw.write(array[i][j] + " , ");

					}
				}

			}

			bw.newLine();

		}

		Console.printLine("\tdone");

		bw.close();

	}

	public static String[][] listToArray(LinkedList<String[]> list) {

		String[][] output = new String[list.size()][list.get(0).length];

		for (int i = 0; i < output.length; i++) {

			for (int j = 0; j < output[0].length; j++) {

				output[i][j] = list.get(i)[j];

			}

			Console.printLine("converting list to array: " + i + "/" + output.length);

		}

		return output;

	}

	public static String[][] readPreviousMatchesArray() throws IOException {
		int lines = countLines("matches.txt") - 1;

		String[][] readMatches = new String[lines][9];
		BufferedReader br = new BufferedReader(new FileReader("matches.txt"));

		String[] deck = new String[9];

		for (int i = 0; i < lines; i++) {
			String line = br.readLine();
			//System.out.println("reading match: " + i);
			if (line == null || line.equals("")) {
				// idfk
			} else {
				deck = line.split(" , ");
				if (deck != null && deck.length > 9)
					readMatches[i] = (deck);
			}
		}

		br.close();
		return readMatches;

	}

	public static LinkedList<String[]> readPreviousMatches() throws IOException {

		LinkedList<String[]> readMatches = new LinkedList<String[]>();
		BufferedReader br = new BufferedReader(new FileReader("matches.txt"));

		String[] deck = new String[9];
		int lines = countLines("matches.txt");

		for (int i = 0; i < lines; i++) {
			String line = br.readLine();
			if (line == null) {
				// idfk
			} else {
				deck = line.split(" , ");
				if (deck != null && deck.length > 9)
					readMatches.add(deck);
			}
			Console.printLine(i + "/" + lines);
		}

		br.close();
		return readMatches;

	}

	public static int countLines(String path) throws IOException {
		FileReader input = new FileReader(path);
		LineNumberReader count = new LineNumberReader(input);

		int lines = (int) count.lines().count() + 1;
		count.close();

		return lines;
	}

	public static String[] usedTags = new String[1000000];
	public static int index;

	public static void getUsedTags() {

		for (int i = 0; i < me.stun.data.DeckData.totalMatches.length; i++) {
			usedTags[i] = me.stun.data.DeckData.totalMatches[i][8];
		}
		index = me.stun.data.DeckData.totalMatches.length;
		for (int i = index; i < usedTags.length; i++) {
			usedTags[i] = " ";
		}

	}

	public static int counter2 = 0;

	public static void addNewMatches(LinkedList<String[]> matches) {

		Console.print("\nadding new matches..");

		boolean found = false;
		@SuppressWarnings("unused")
		int counter = 0;
		int matchesSize = matches.size();
		int tagsSize = usedTags.length - 1;

		if (tagsSize <= 1 || me.stun.data.DeckData.totalMatches[0].length <= 1) {
			for (int i = 0; i < matchesSize; i++) {
				addedMatches.add(matches.get(i));
				counter++;
				Console.printLine("added cause no levels..");
			}
		} else {

			for (int i = 0; i < matchesSize; i++) {

				for (int j = 0; j < tagsSize; j++) {

					String temp = matches.get(i)[8];
					String temp1 = matches.get(i)[11];

					// System.out.println(
					// "compared " + temp + " to " + usedTags[j] + "\tj = " + j + "\ttagsSize = " +
					// tagsSize);

					if (j < me.stun.data.DeckData.totalMatches.length) {
						if (temp.equals(usedTags[j])
								&& temp1.equals(me.stun.data.DeckData.totalMatches[j][11])) {
							// if (temp.equals(usedTags[j])) {
							found = true;
							usedTags[index] = temp;
							j = tagsSize;
							index++;
						}
					}

				}

				if (found == false) {
					addedMatches.add(matches.get(i));
					counter++;
				} else {
					found = false;
					counter2++;
				}

			}
		}
		Console.print("\t done");

	}

	public static String[][] addMatchesToArray(LinkedList<String[]> addedMatches, String[][] totalMatches) {
		Console.print("\n\nadding matches to array..");
		String[][] output = new String[addedMatches.size() + totalMatches.length][totalMatches[0].length];

		for (int i = 0; i < totalMatches.length; i++) {

			output[i] = totalMatches[i];

		}

		for (int i = 0; i < addedMatches.size(); i++) {

			output[i + totalMatches.length] = addedMatches.get(i);

		}

		Console.print("\t\tdone");

		return output;

	}
	
}
