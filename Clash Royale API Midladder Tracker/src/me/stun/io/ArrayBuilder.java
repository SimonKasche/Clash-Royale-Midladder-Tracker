package me.stun.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;

public class ArrayBuilder {

	public static void printArray(String[][] array) {
	
		for (int i = 0; i < array.length; i++) {
	
			System.out.print(i + "\t");
	
			for (int j = 0; j < array[0].length; j++) {
	
				System.out.print(array[i][j] + " ");
	
			}
	
			System.out.print("\n");
	
		}
	
	}
	

	public static void writeArray(String[][] array) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("matches.txt"));
		
		for (int i = 0; i < array.length; i++) {
	
			for (int j = 0; j < array[i].length; j++) {
				
				if (j == array[i].length - 1) {
					
					bw.write(array[i][j]);
					
				} else {
					
					bw.write(array[i][j] + " , ");
					
				}
	
			}
	
			bw.newLine();
	
		}
		
		bw.close();
	
	}

	public static String[][] readPreviousMatchesArray() throws IOException {
		
		int lines = me.stun.io.ArrayBuilder.countLines("matches.txt") - 1;		
		
		String[][] readMatches = new String[lines][9];
		BufferedReader br = new BufferedReader(new FileReader("matches.txt"));
		System.out.println("reading matches..");
	
		String[] deck = new String[9];
		int steps = lines / 35;
	
		for (int i = 0; i < lines; i++) {
			String line = br.readLine();
			if (line == null || line.equals("")) {
				// idfk
			} else {
				deck = line.split(" , ");
				if (deck != null && deck.length > 9)
					readMatches[i] = (deck);
			}
			
			if(steps != 0 && i % steps == 0) {
				me.stun.startup.StartupImage.progressbar.setValue(me.stun.startup.StartupImage.progressbar.getValue() + 1);
			}
			
		}
	
		br.close();
		return readMatches;
	
	}

	public static void saveMatches(LinkedList<String[]> matches) throws IOException {
	
		BufferedWriter bw = new BufferedWriter(new FileWriter("matches.txt"));
		for (int i = 0; i < matches.size(); i++) {
			for (int j = 0; j < matches.get(i).length; j++) {
				if (j == matches.get(i).length - 1) {
					bw.write(matches.get(i)[j]);
				} else {
					bw.write(matches.get(i)[j] + " , ");
				}
			}
			bw.newLine();
		}
		bw.close();
	
		me.stun.startup.Console.TextArea.append("successfully saved matches to 'matches.txt'\n");
	
	}
	
	public static int[] listToArray(LinkedList<Integer> list) {

		int[] output = new int[list.size()];

		for (int i = 0; i < output.length; i++) {

			output[i] = list.get(i);

		}

		return output;

	}

	public static int countLines(String path) throws IOException {
		FileReader input = new FileReader(path);
		LineNumberReader count = new LineNumberReader(input);
	
		int lines = (int) count.lines().count() + 1;
		count.close();
	
		return lines;
	}

}
