
public class MinimumPenaltyForAShop {
	public int bestClosingTime(String customers) {
		char[] customersArrival = customers.toCharArray();
		int length = customersArrival.length;
		int bestClosingTime = 0;
		int currentPenalty = 0;
		int minimumPenalty = 0;

		// Get penalty for closing at 0th hour
		for (int i = 0; i < length; i++) {
			if (customersArrival[i] == 'Y') {
				++currentPenalty;
			}
		}

		// Get penalty for rest of the hours
		currentPenalty = minimumPenalty;
		for (int i = 1; i <= length; i++) {
			if (customersArrival[i - 1] == 'N') {
				++currentPenalty;
			}
			if (customersArrival[i - 1] == 'Y') {
				--currentPenalty;
			}

			if (currentPenalty < minimumPenalty) {
				minimumPenalty = currentPenalty;
				bestClosingTime = i;
			}
		}

		return bestClosingTime;
	}
}
