import java.util.Arrays;

public class CoinsChange {

	public int coinChange(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}

		int count[] = new int[amount + 1];
		Arrays.sort(coins);

		for (int i = 0; i < coins.length; i++) {
			for (int j = coins[i]; j <= amount; j++) {
				int toCheck = j - coins[i];
				int oldCount = count[j];
				int newCount = 0;
				if (toCheck == 0) {
					newCount = 1;
				} else {
					if (count[toCheck] > 0) {
						newCount = 1 + count[toCheck];
					} else {
						newCount = 0;
					}
				}

				if (oldCount == 0) {
					count[j] = newCount;
				} else if (newCount == 0) {
					count[j] = oldCount;
				} else {
					count[j] = Math.min(oldCount, newCount);
				}
			}
		}

		return count[amount] > 0 ? count[amount] : -1;
	}

	public static void main(String args[]) {
		CoinsChange obj = new CoinsChange();
		obj.coinChange(new int[] { 1, 2, 5 }, 11);
	}
}
