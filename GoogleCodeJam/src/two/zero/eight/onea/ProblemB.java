package two.zero.eight.onea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class ProblemB {

	private static String smallFile = "/home/priyanka/Documents/source/sunny/GoogleCodeJam/src/two/zero/eight/onea/B-small-practice.in";

	private static String largeFile = "/home/priyanka/Documents/source/sunny/GoogleCodeJam/src/two/zero/eight/onea/B-large-practice.in";

	public static void main(String args[]) throws Exception {
		processFile(smallFile);
		processFile(largeFile);
	}

	private static void processFile(String input) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(input));
		PrintWriter pw = new PrintWriter(new FileWriter(input.replace("practice", "solution")));
		int testCases = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCases; i++) {
			int flavours = Integer.parseInt(br.readLine());
			int customers = Integer.parseInt(br.readLine());
			int[][] customerLikes = new int[customers][flavours + 1];
			for (int j = 0; j < customers; j++) {
				String[] likes = br.readLine().split(" ");
				int likesCount = Integer.parseInt(likes[0]);
				likes = Arrays.copyOfRange(likes, 1, likes.length);
				for (int k = 0; k < likesCount; k++) {
					int factor = 2 * k;
					int like = Integer.parseInt(likes[factor]);
					int likeType = Integer.parseInt(likes[factor + 1]) + 1;
					customerLikes[j][like] = likeType;
				}
			}

			int[] toPrepare = toPrepare(customerLikes, flavours + 1, customers);
			String result = "IMPOSSIBLE";
			if (toPrepare != null) {
				result = "";
				for (int j = 1; j < toPrepare.length; j++) {
					result += toPrepare[j] + " ";
				}
			}

			pw.println("Case #" + (i + 1) + ": " + result.trim());
		}

		br.close();
		pw.flush();
		pw.close();
	}

	private static int[] toPrepare(int[][] customerLikes, int flavours, int customers) {
		int[] toPrepare = new int[flavours];
		int happyCount = 0;

		// Clear customers who can be satisfied with un malted flavors
		boolean changed = true;
		while (changed) {
			int maxColumn = -1;
			int maxCount = 0;
			changed = false;

			// Find the flavor with maximum un malted likes
			for (int i = 0; i < flavours; i++) {
				int count = 0;
				for (int j = 0; j < customers; j++) {
					if (customerLikes[j][i] == 2) {
						count = 0;
						break;
					}

					if (customerLikes[j][i] == 1) {
						count++;
					}
				}

				if (count > maxCount) {
					maxCount = count;
					maxColumn = i;
				}
			}

			// Clear all the satisfied customers
			if (maxColumn != -1) {
				changed = true;
				toPrepare[maxColumn] = 0;
				for (int i = 0; i < customers; i++) {
					if (customerLikes[i][maxColumn] == 1) {
						happyCount++;
						for (int j = 0; j < flavours; j++) {
							customerLikes[i][j] = 0;
						}
					}
				}
			}
		}

		// Clear customers who can be satisfied with un malted flavors
		changed = true;
		while (changed) {
			int maxColumn = -1;
			int maxCount = 0;
			changed = false;

			// Find the flavor with maximum un malted likes
			for (int i = 0; i < flavours; i++) {
				int count = 0;
				for (int j = 0; j < customers; j++) {
					if (customerLikes[j][i] == 1) {
						count = 0;
						break;
					}

					if (customerLikes[j][i] == 2) {
						count++;
					}
				}

				if (count > maxCount) {
					maxCount = count;
					maxColumn = i;
				}
			}

			// Clear all the satisfied customers
			if (maxColumn != -1) {
				changed = true;
				toPrepare[maxColumn] = 1;
				for (int i = 0; i < customers; i++) {
					if (customerLikes[i][maxColumn] == 2) {
						happyCount++;
						for (int j = 0; j < flavours; j++) {
							customerLikes[i][j] = 0;
						}
					}
				}
			}
		}

		if (happyCount != customers) {
			return null;
		}

		return toPrepare;
	}
}
