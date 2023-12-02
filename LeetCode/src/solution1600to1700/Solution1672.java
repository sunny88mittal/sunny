package solution1600to1700;

public class Solution1672 {
	public int maximumWealth(int[][] accounts) {
		int max = 0;
		for (int i = 0; i < accounts.length; i++) {
			int wealth = 0;
			for (int j = 0; j < accounts[i].length; i = j++) {
				wealth += accounts[i][j];
			}
			if (max < wealth) {
				max = wealth;
			}
		}

		return max;
	}
}
