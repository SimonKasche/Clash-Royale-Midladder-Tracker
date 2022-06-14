package me.stun.repair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import me.stun.startup.Console;

public class IO {
	
	public static void writeArray(String[][] array) throws IOException {

		Console.print("\nwriting array to matches.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("matches.txt"));

		boolean newline = true;

		for (int i = 0; i < array.length; i++) {

			for (int j = 0; j < array[i].length; j++) {

				if (array[i] != null && array[i][j] != null) {

					if (j == array[i].length - 1) {

						bw.write(array[i][j]);

					} else {

						bw.write(array[i][j] + " , ");

					}

					newline = true;

				} else {
					newline = false;
				}

			}
			if (newline == true) {

				bw.newLine();
			}

		}

		Console.printLine("\tdone");

		bw.close();

	}
	
}
