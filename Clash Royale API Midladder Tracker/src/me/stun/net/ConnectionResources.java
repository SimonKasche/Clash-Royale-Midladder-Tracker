package me.stun.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

import me.stun.io.ArrayBuilder;

public class ConnectionResources {

	public static void getPlayerData() throws IOException, ParseException {
		
		me.stun.data.DataProcessor.playerTags = readFile("src/PlayerTag.txt");
		me.stun.startup.Console.TextArea.append("establishing connection with api.clashroyale.com..\n");
		
		int steps = me.stun.data.DataProcessor.playerTags.length-1;
		int stepsize = 0;
		stepsize = 10 / steps;

		for (me.stun.data.DataProcessor.playerTagCounter = 0; me.stun.data.DataProcessor.playerTagCounter < me.stun.data.DataProcessor.playerTags.length
				- 1; me.stun.data.DataProcessor.playerTagCounter++) {

			// read battle history from clash royale api
			Connection.getBattleHistory(me.stun.data.DataProcessor.playerTags[me.stun.data.DataProcessor.playerTagCounter]);

			LinkedList<String[]> matches = me.stun.io.ParseJsonFile.readBattleHistory();
			me.stun.data.DataProcessor.addNewMatches(matches);
			
			me.stun.startup.StartupImage.progressbar.setValue(me.stun.startup.StartupImage.progressbar.getValue() + stepsize);

		}

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

			String[] output = new String[ArrayBuilder.countLines(path)];

			for (int i = 0; i < ArrayBuilder.countLines(path); i++) {
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
			System.out.println("File '" + path + "' could not be found");
			return null;
		}

	}
	

}
