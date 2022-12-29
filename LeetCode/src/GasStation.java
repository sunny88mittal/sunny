
public class GasStation {

	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start = 0;
		int count = 0;
		int remainingGas = 0;
		for (int i = 0; i < 2 * gas.length; i++) {
			int k = i % (gas.length);
			remainingGas += gas[k] - cost[k];
			if (remainingGas >= 0) {
				if (count == 0) {
					start = k;
				}
				++count;
			} else {
				count = 0;
				remainingGas = 0;
			}

			if (count == gas.length) {
				return start;
			}
		}

		return -1;
	}

	public static void main(String args[]) {
		int gas[] = new int[] { 1, 2, 3, 4, 5 };
		int cost[] = new int[] { 3, 4, 5, 1, 2 };
		GasStation obj = new GasStation();
		obj.canCompleteCircuit(gas, cost);
	}
}
