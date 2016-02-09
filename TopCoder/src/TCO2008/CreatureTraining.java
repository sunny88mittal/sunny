package TCO2008;

public class CreatureTraining {

	private int gcount[];

	private int gpower[];

	int N;

	private long memo[][][] = new long[52][102][102];

	public long maximumPower(int[] count, int[] power, int D) {
		gcount = count;
		gpower = power;
		N = count.length;
		for (int i = 0; i < 52; i++) {
			for (int j = 0; j < 102; j++) {
				for (int k = 0; k < 102; k++) {
					memo[i][j][k] = -1;
				}
			}
		}
		return solve(0, 0, D);
	}

	private long solve(int level, int add, int upgrades) {
		long res = memo[level][add][upgrades];
		if (res >= 0)
			return res;
		res = 0;
		if (level == N)
			return res;

		int maxUpgrades = Math.min(upgrades, gcount[level] + add);
		for (int now = 0; now <= maxUpgrades; now++) {
			long thisLevel = (long)gpower[level] * (long)(gcount[level] + add - now);
			long nextLevels = solve(level + 1, now, upgrades - now);
			res = Math.max(res, thisLevel + nextLevels);
		}

		memo[level][add][upgrades] = res;
		return res;
	}
}
