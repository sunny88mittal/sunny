package two.zero.eight.onea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
		Set<Integer> processedFlavour = new HashSet<Integer>();
		Set<Integer> processedCustomer = new HashSet<Integer>();
		processedFlavour.add(0);

		while (true) {
			// Clear customers who can be satisfied with un malted flavors
			tryType(customerLikes, flavours, customers, processedFlavour, processedCustomer, toPrepare, 1);

			// Try un malted and then malted for remaining customers
			tryType(customerLikes, flavours, customers, processedFlavour, processedCustomer, toPrepare, 2);

			// Force Unmalted
			boolean changed = forcedTry(customerLikes, flavours, customers, processedFlavour, processedCustomer, toPrepare, 1);
			if (changed) {
				continue;
			}

			// Force Malted
			changed = forcedTry(customerLikes, flavours, customers, processedFlavour, processedCustomer, toPrepare, 2);
			if (!changed) {
				break;
			}
		}

		if (processedCustomer.size() != customers) {
			return null;
		}

		return toPrepare;
	}

	private static void tryType(int[][] customerLikes, int flavours, int customers, Set<Integer> processedFlavour,
			Set<Integer> processedCustomer, int[] toPrepare, int toTry) {
		boolean changed = true;
		while (changed) {
			changed = false;
			for (int i = 0; i < flavours; i++) {
				if (!processedFlavour.contains(i)) {
					int count = 0;
					for (int j = 0; j < customers; j++) {
						if (!processedCustomer.contains(j)) {
							if (customerLikes[j][i] == toTry) {
								count++;
								continue;
							}

							if (customerLikes[j][i] > 0) {
								count = 0;
								break;
							}
						}
					}

					if (count > 0) {
						toPrepare[i] = toTry - 1;
						changed = true;
						processedFlavour.add(i);
						for (int k = 0; k < customers; k++) {
							if (customerLikes[k][i] == toTry) {
								processedCustomer.add(k);
							}
						}
					}
				}
			}
		}
	}

	private static boolean forcedTry(int[][] customerLikes, int flavours, int customers, Set<Integer> processedFlavour,
			Set<Integer> processedCustomer, int[] toPrepare, int toTry) {
		boolean changed = false;
		for (int i = 0; i < customers; i++) {
			if (!processedCustomer.contains(i)) {
				for (int j = 0; j < flavours; j++) {
					if (!processedFlavour.contains(j) && customerLikes[i][j] == toTry) {
						changed = true;
						processedFlavour.add(j);
						processedCustomer.add(i);
						toPrepare[j] = toTry - 1;
						break;
					}
				}
			}

			if (changed) {
				break;
			}
		}
		return changed;
	}
}
