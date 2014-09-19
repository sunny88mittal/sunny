package SRM466;

import java.util.Arrays;

public class LotteryTicket {
	
	public String buy(int price, int b1, int b2, int b3, int b4) {
		String possible = "POSSIBLE";
		String impossible  = "IMPOSSIBLE";
		
		int [] prices =  new int[] {b1, b2, b3, b4};
		Arrays.sort(prices);
		for (int i=0;i<4; i++) {
			if (price == prices[i]) {
				return possible;
			}
			
			if (prices[i] > price) {
				continue;
			}
			
			for (int j=i+1; j<4; j++) {
				int sum2 = prices[i] + prices[j];
				if (price == sum2){
					return possible;
				}
				
				if (sum2 > price) {
					continue;
				}
				
				for (int k=j+1; k<4; k++) {
					int sum3 = prices[i] + prices[j] + prices[k];
					if (price == sum3){
						return possible;
					}
					
					if (sum3 > price) {
						continue;
					}
					
					for (int l=k+1; l<4; l++) {
						int sum4 = prices[i] + prices[j] + prices[k] + prices[l];
						if (price == sum4){
							return possible;
						}
					}
				}
			}
		}
		
		return impossible;
	}
}
