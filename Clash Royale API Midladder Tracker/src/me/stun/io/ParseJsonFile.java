package me.stun.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJsonFile {

	public static LinkedList<String[]> readBattleHistory() throws FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();

		JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("output.json"));
		LinkedList<String[]> matches = new LinkedList<String[]>();

		for (int i = 0; i < jsonArray.size(); i++) {

			JSONObject match = (JSONObject) jsonArray.get(i);
			JSONArray opponentArray = (JSONArray) match.get("opponent");
			JSONObject opponent = (JSONObject) opponentArray.get(0);
			JSONArray cards = (JSONArray) opponent.get("cards");

			JSONArray playerArray = (JSONArray) match.get("team");
			JSONObject player = (JSONObject) playerArray.get(0);

			JSONObject gameModeArray = (JSONObject) match.get("gameMode");
			String gamemode = (String) gameModeArray.get("name");
			if (gamemode.toLowerCase().contains("ladder")) {

				String[] deck = new String[12]; // 0-7 = cards | 8 = opponentTag | 9 = playerTag | 10 = cups | 11 = time

				for (int j = 0; j < cards.size(); j++) {
					JSONObject jsonCard = (JSONObject) cards.get(j);

					String card = (String) jsonCard.get("name");
					deck[j] = card;
				}
				deck[8] = (String) opponent.get("tag");
				deck[9] = (String) player.get("tag");
				deck[10] = Long.toString((Long) opponent.get("startingTrophies"));
				deck[11] = (String) match.get("battleTime");

				matches.add(deck);
			}

		}

		return matches;

	}
	
public static String readClanTag() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("output.json"));
		JSONObject clan = (JSONObject) jsonObject.get("clan");

		try {
			String clanTag = (String) clan.get("tag");

			StringBuilder sb = new StringBuilder(clanTag);
			sb.deleteCharAt(0);
			sb.insert(0, '3');
			sb.insert(0, '2');
			sb.insert(0, '%');
			return sb.toString();
		} catch (Exception e) {

		}

		return null;

	}

	public static LinkedList<String> readClanPlayers() throws FileNotFoundException, IOException, ParseException {

		LinkedList<String> output = new LinkedList<String>();

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("output.json"));
		JSONArray members = (JSONArray) jsonObject.get("memberList");

		for (int i = 0; i < members.size(); i++) {

			JSONObject player = (JSONObject) members.get(i);
			String tag = (String) player.get("tag");

			StringBuilder sb = new StringBuilder(tag);
			sb.deleteCharAt(0);
			sb.insert(0, '3');
			sb.insert(0, '2');
			sb.insert(0, '%');

			output.add(sb.toString());

		}

		return output;

	}

	public static LinkedList<String> readBattleHistoryTags() throws FileNotFoundException, IOException, ParseException {

		LinkedList<String> output = new LinkedList<String>();

		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("output.json"));

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject match = (JSONObject) jsonArray.get(i);
			JSONArray opponentArray = (JSONArray) match.get("opponent");
			JSONObject opponent = (JSONObject) opponentArray.get(0);
			String tag = (String) opponent.get("tag");

			StringBuilder sb = new StringBuilder(tag);
			sb.deleteCharAt(0);
			sb.insert(0, '3');
			sb.insert(0, '2');
			sb.insert(0, '%');

			output.add(sb.toString());
		}

		return output;

	}
}
