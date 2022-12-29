
public class BestTimeToBuyAndSellStock {

	public int maxProfit(int[] prices) {
		int buyingIndex = 0;
		int sellingIndex = 0;
		int maximumReturn = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < prices[buyingIndex]) {
				if (prices[sellingIndex] - prices[buyingIndex] > maximumReturn) {
					maximumReturn = prices[sellingIndex] - prices[buyingIndex];
				}
				buyingIndex = i;
				sellingIndex = i;
			}

			if (prices[i] > prices[sellingIndex]) {
				sellingIndex = i;
			}
		}

		return maximumReturn > prices[sellingIndex] - prices[buyingIndex] ? maximumReturn
				: prices[sellingIndex] - prices[buyingIndex];
	}

	public static void main(String args[]) {
		BestTimeToBuyAndSellStock obj = new BestTimeToBuyAndSellStock();
		obj.maxProfit(new int[] { 2, 4, 1 });
	}
}
