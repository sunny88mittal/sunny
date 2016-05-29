package SRM216;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentRanker {

	private Map<String, Integer> playerWins = new HashMap<String, Integer>();
	private Map<String, String> lostToList = new HashMap<String, String>();

	public String[] rankTeams(String[] names, String[] lostTo) {

		int n = names.length;

		for (String name : names) {
			playerWins.put(name, 0);
		}

		for (int i = 0; i < n; i++) {
			String winner = lostTo[i];
			String looser = names[i];
			Integer winCount = playerWins.get(winner);
			if (!winner.equals("")) {
				playerWins.put(winner, winCount + 1);
				lostToList.put(looser, winner);
			}
		}

		List<String> namesList = Arrays.asList(names);
		Collections.sort(namesList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (playerWins.get(o1) != playerWins.get(o2)) {
					return Integer.compare(playerWins.get(o1), playerWins.get(o2));
				}
				return compare(lostToList.get(o1), lostToList.get(o2));
			}
		});
		
		Collections.reverse(namesList);
		
		return (String[]) namesList.toArray();
	}

}
