package me.stun.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import me.stun.startup.Console;

public class Connection {

	public static boolean failure = false;

	private static String baseURL = "https://api.clashroyale.com/v1";

	public static void getBattleHistory(String playerTag) throws IOException {

		HttpURLConnection connection;
		URL url = new URL(baseURL + "/players/" + playerTag + "/battlelog");

		String ApiKey = ConnectionResources.getApiKey();
		String auth = "Bearer " + ApiKey;

		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", auth);
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		int status = connection.getResponseCode();

		if (status > 299) {
			try {
				me.stun.startup.Console.TextArea.append("connection failed, exit code " + status + "\n");
				failure = true;

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				LinkedList<String> response = new LinkedList<String>();
				String line;
				while ((line = reader.readLine()) != null) {
					response.add(line);
				}

				for (int i = 0; i < response.size(); i++) {
					me.stun.startup.Console.TextArea.append(response.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {

			//int counter = me.stun.data.DataProcessor.playerTags.length - 1;
			//int playercounter = me.stun.data.DataProcessor.playerTagCounter + 1;
			//me.stun.startup.Console.TextArea.append(
			//		"requesting match history for '" + playerTag + "' (" + playercounter + "/" + counter + ")..\n");
			me.stun.startup.Console.TextArea
					.setCaretPosition(me.stun.startup.Console.TextArea.getDocument().getLength());

			LinkedList<String> response = new LinkedList<String>();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				response.add(line);
			}

			String[] matches = response.toString().split("\n");
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.json"));

			StringBuilder sb = new StringBuilder(matches[0]);
			sb.deleteCharAt(matches[0].length() - 1);
			sb.deleteCharAt(0);
			matches[0] = sb.toString();

			bw.write(matches[0]);

			bw.close();

			//me.stun.startup.Console.TextArea.append("request successfull\n");
			me.stun.startup.Console.TextArea
					.setCaretPosition(me.stun.startup.Console.TextArea.getDocument().getLength());
			connection.disconnect();
		}
	}
	
	
	public static void getPlayerClanTag(String playerTag) throws IOException {

		HttpURLConnection connection;
		URL url = new URL(baseURL + "/players/" + playerTag);

		String ApiKey = ConnectionResources.getApiKey();
		String auth = "Bearer " + ApiKey;

		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", auth);
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		int status = connection.getResponseCode();
		
		if (status > 299) {
			try {
				Console.printLine("connection failed, exit code " + status + "\n");
				failure = true;

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				LinkedList<String> response = new LinkedList<String>();
				String line;
				while ((line = reader.readLine()) != null) {
					response.add(line);
				}

				for (int i = 0; i < response.size(); i++) {
					Console.printLine(response.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			BufferedWriter bw = new BufferedWriter(new FileWriter("output.json"));
			String response = reader.readLine();
			bw.write(response);
			bw.close();

			connection.disconnect();
		}
	}

	public static void getClanPlayers(String clanTag) throws IOException {

		HttpURLConnection connection;
		URL url = new URL(baseURL + "/clans/" + clanTag);

		String ApiKey = ConnectionResources.getApiKey();
		String auth = "Bearer " + ApiKey;

		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", auth);
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		int status = connection.getResponseCode();
		
		if (status > 299) {
			try {
				Console.printLine("connection failed, exit code " + status + "\n");
				failure = true;

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				LinkedList<String> response = new LinkedList<String>();
				String line;
				while ((line = reader.readLine()) != null) {
					response.add(line);
				}

				for (int i = 0; i < response.size(); i++) {
					Console.printLine(response.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			BufferedWriter bw = new BufferedWriter(new FileWriter("output.json"));
			String response = reader.readLine();
			bw.write(response);
			bw.close();

			connection.disconnect();
		}
	}


}
