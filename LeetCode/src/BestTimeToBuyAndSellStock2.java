
public class BestTimeToBuyAndSellStock2 {

	public int maxProfit(int[] prices) {
		int buy = 0;
		int sell = 0;
		int sum = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] > prices[sell]) {
				sell = i;
			} else if (prices[i] < prices[sell]) {
				sum += prices[sell] - prices[buy];
				buy = i;
				sell = i;
			}
		}

		sum += prices[sell] - prices[buy];
		return sum;
	}
}
