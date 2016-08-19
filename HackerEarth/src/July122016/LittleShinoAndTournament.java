package July122016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LittleShinoAndTournament {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		int N = Integer.parseInt(str.split(" ")[0]);
		int Q = Integer.parseInt(str.split(" ")[1]);

		List<Integer> strengths = new ArrayList<Integer>(N);
		Map<Integer, Integer> strPos = new HashMap<Integer, Integer>();
		Map<Integer, Integer> posWins = new HashMap<Integer, Integer>();
		String strengthsStr[] = scan.nextLine().split(" ");
		for (int i = 0; i < N; i++) {
			strengths.add(Integer.parseInt(strengthsStr[i]));
			strPos.put(strengths.get(i), i);
			posWins.put(i, 0);
		}

		int[] queries = new int[Q];
		for (int i = 0; i < Q; i++) {
			queries[i] = Integer.parseInt(scan.nextLine());
		}

		while (strengths.size() != 1) {
			List<Integer> nstrengths = new ArrayList<Integer>();
			for (int i = 0; i < strengths.size(); i = i + 2) {
				int strnth1 = strengths.get(i);
				if (i + 1 < strengths.size()) {
					int strnth2 = strengths.get(i+1);
					if (strnth1 > strnth2) {
						nstrengths.add(strnth1);
					} else {
						nstrengths.add(strnth2);
					}
					
					int pos = strPos.get(strnth1);
					int wins = posWins.get(pos);
					posWins.put(pos, ++wins);
					pos = strPos.get(strnth2);
					wins = posWins.get(pos);
					posWins.put(pos, ++wins);
				} else {
					nstrengths.add(strnth1);
				}
			}
			strengths = nstrengths;
		}

		for (int i = 0; i < N; i++) {
			System.out.println(posWins.get(queries[i] - 1));
		}
	}
}
