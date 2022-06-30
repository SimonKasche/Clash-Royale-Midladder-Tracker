package me.stun.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DeckData {

	public static Map<String, Integer> cardCounter = new HashMap<String, Integer>();
	public static Map<String, Integer> sortedCardCounter;

	// public static String[][] totalMatches;

	public static String[][] matchesToArray(LinkedList<String[]> matches) {

		String[][] output = new String[matches.size()][matches.get(0).length];

		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < output[i].length; j++) {
				output[i][j] = matches.get(i)[j];
			}

		}

		return output;

	}

	public static void getCardUsage(String[][] matches) {

		int counter = 0;

		me.stun.startup.StartupImage.plotProgressbar.setValue(0);
		int stepSize = cards.length / 100;
		me.stun.startup.StartupImage.plotProgressbar.setVisible(true);
		int steps = cards.length / 35;

		for (int i = 0; i < cards.length; i++) {

			for (int j = 0; j < matches.length; j++) {

				for (int k = 0; k < 9; k++) {

					if (cards[i].equals(matches[j][k])) {

						counter++;
						k = 8;

					}

				}

			}

			cardCounter.put(cards[i], counter);
			counter = 0;

			if (i % stepSize == 0)
				me.stun.startup.StartupImage.plotProgressbar
						.setValue(me.stun.startup.StartupImage.plotProgressbar.getValue() + 1);
			if (i % steps == 0)
				me.stun.startup.StartupImage.progressbar
						.setValue(me.stun.startup.StartupImage.progressbar.getValue() + 1);

		}

		sortedCardCounter = sortByValue(cardCounter, false);

		me.stun.startup.StartupImage.plotProgressbar.setVisible(false);

	}

	public static float getSpellAverage(String[][] matches) {

		int counter = 0;

		int[] usage = new int[matches.length];

		me.stun.startup.StartupImage.plotProgressbar.setValue(0);
		int stepSize = matches.length / 100;
		me.stun.startup.StartupImage.plotProgressbar.setVisible(true);

		for (int i = 0; i < matches.length; i++) {

			for (int j = 0; j < spells.length; j++) {

				for (int k = 0; k < 8; k++) {

					if (spells[j].equals(matches[i][k])) {

						counter++;
						k = 8;

					}

				}

			}
			usage[i] = counter;
			counter = 0;

			if (stepSize != 0) {
				if (i % stepSize == 0)
					me.stun.startup.StartupImage.plotProgressbar
							.setValue(me.stun.startup.StartupImage.plotProgressbar.getValue() + 1);
			}

		}

		float sum = 0;
		for (int i = 0; i < usage.length; i++) {

			sum = sum + usage[i];

		}

		me.stun.startup.StartupImage.plotProgressbar.setVisible(false);
		return (float) sum / usage.length;

	}

	public static float getSplashAverage(String[][] matches) {

		int counter = 0;

		int[] usage = new int[matches.length];

		me.stun.startup.StartupImage.plotProgressbar.setValue(0);
		int stepSize = matches.length / 100;
		me.stun.startup.StartupImage.plotProgressbar.setVisible(true);

		for (int i = 0; i < matches.length; i++) {

			for (int j = 0; j < splashCards.length; j++) {

				for (int k = 0; k < 8; k++) {

					if (splashCards[j].equals(matches[i][k])) {

						counter++;
						k = 8;

					}

				}

			}
			usage[i] = counter;
			counter = 0;

			if (stepSize != 0) {
				if (i % stepSize == 0)
					me.stun.startup.StartupImage.plotProgressbar
							.setValue(me.stun.startup.StartupImage.plotProgressbar.getValue() + 1);
			}

		}

		float sum = 0;
		for (int i = 0; i < usage.length; i++) {

			sum = sum + usage[i];

		}

		me.stun.startup.StartupImage.plotProgressbar.setVisible(false);
		return (float) sum / usage.length;

	}

	public static float getWinConditionAverage(String[][] matches) {

		int counter = 0;

		int[] usage = new int[matches.length];

		me.stun.startup.StartupImage.plotProgressbar.setValue(0);
		int stepSize = matches.length / 100;
		me.stun.startup.StartupImage.plotProgressbar.setVisible(true);

		for (int i = 0; i < matches.length; i++) {

			for (int j = 0; j < winConditions.length; j++) {

				for (int k = 0; k < 8; k++) {

					if (winConditions[j].equals(matches[i][k])) {

						counter++;
						k = 8;

					}

				}

			}
			usage[i] = counter;
			counter = 0;

			if (stepSize != 0) {
				if (i % stepSize == 0)
					me.stun.startup.StartupImage.plotProgressbar
							.setValue(me.stun.startup.StartupImage.plotProgressbar.getValue() + 1);
			}

		}

		float sum = 0;
		for (int i = 0; i < usage.length; i++) {

			sum = sum + usage[i];

		}
		me.stun.startup.StartupImage.plotProgressbar.setVisible(false);
		return (float) sum / usage.length;

	}

	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order) {
		List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

		// Sorting the list based on values
		list.sort((o1, o2) -> order
				? o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey())
						: o1.getValue().compareTo(o2.getValue())
				: o2.getValue().compareTo(o1.getValue()) == 0 ? o2.getKey().compareTo(o1.getKey())
						: o2.getValue().compareTo(o1.getValue()));
		return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

	}

	public static String[] cards = { "Three Musketeers", "Golem", "Electro Giant", "Royal Recruits", "P.E.K.K.A",
			"Lava Hound", "Mega Knight", "Royal Giant", "Elite Barbarians", "Giant Skeleton", "Goblin Giant", "Sparky",
			"Archer Queen", "Barbarians", "Minion Horde", "Rascals", "Balloon", "Witch", "Prince", "Bowler",
			"Executioner", "Cannon Cart", "Electro Dragon", "Ram Rider", "Giant", "Royal Hogs", "Wizard",
			"Skeleton King", "Golden Knight", "Skeleton Dragons", "Baby Dragon", "Dark Prince", "Hunter", "Lumberjack",
			"Inferno Dragon", "Electro Wizard", "Night Witch", "Magic Archer", "Mother Witch", "Valkyrie", "Musketeer",
			"Mini P.E.K.K.A", "Hog Rider", "Battle Ram", "Zappies", "Flying Machine", "Battle Healer", "Knight",
			"Archers", "Minions", "Goblin Gang", "Skeleton Barrel", "Firecracker", "Skeleton Army", "Guards",
			"Ice Wizard", "Princess", "Miner", "Bandit", "Royal Ghost", "Fisherman", "Mega Minion", "Dart Goblin",
			"Elixir Golem", "Goblins", "Bomber", "Spear Goblins", "Bats", "Wall Breakers", "Ice Golem", "Skeletons",
			"Ice Spirit", "Fire Spirit", "Electro Spirit", "Heal Spirit", "Barbarian Hut", "X-Bow", "Elixir Collector",
			"Goblin Hut", "Inferno Tower", "Mortar", "Tesla", "Goblin Drill", "Bomb Tower", "Furnace", "Goblin Cage",
			"Canon", "Tombstone", "Lightning", "Rocket", "Graveyard", "Freeze", "Poison", "Fireball", "Arrows",
			"Royal Delivery", "Goblin Barrel", "Tornado", "Clone", "Earthquake", "Zap", "Giant Snowball", "Rage",
			"Barbarian Barrel", "The Log", "Mirror", "Mighty Miner" };

	public static String[] spells = { "Lightning", "Rocket", "Graveyard", "Freeze", "Poison", "Fireball", "Arrows",
			"Royal Delivery", "Goblin Barrel", "Tornado", "Clone", "Earthquake", "Zap", "Giant Snowball", "Rage",
			"Barbarian Barrel", "The Log", "Mirror" };

	public static String[] winConditions = { "Skeleton Barrel", "Mortar", "Royal Giant", "Royal Hogs",
			"Three Musketeers", "Elixir Golem", "Battle Ram", "Hog Rider", "Giant", "Wall Breakers", "Goblin Barrel",
			"Goblin Drill", "Balloon", "X-Bow", "Goblin Giant", "Golem", "Electro Giant", "Miner", "Ram Rider",
			"Graveyard", "Lava Hound" };

	public static String[] splashCards = { "Sparky", "Rocket", "Giant Skeleton", "Freeze", "Poison", "Fireball",
			"Arrows", "Royal Delivery", "Mega Knight", "Tornado", "Witch", "Earthquake", "Zap", "Giant Snowball",
			"Bowler", "Barbarian Barrel", "The Log", "Mirror", "Executioner", "Skeleton King", "Wizard",
			"Skeleton Dragons", "Baby Dragon", "Dark Prince", "Hunter", "Electro Wizard", "Magic Archer", "Valkyrie",
			"Firecracker", "Ice Wizard", "Princess", "Royal Ghost", "Bomber", "Ice Golem", "Ice Spirit", "Fire Spirit",
			"Electro Spirit", "Heal Spirit", "Bomb Tower", "Furnace", "Mortar" };

}
