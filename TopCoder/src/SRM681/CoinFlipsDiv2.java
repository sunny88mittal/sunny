package SRM681;

public class CoinFlipsDiv2 {

	public int countCoins(String state) {
		int length = state.length();
		int count = 0;
		for (int i=0; i<length; i++) {
			if (i > 0) {
				if (state.charAt(i-1) != state.charAt(i)) {
					count++;
					continue;
				}	
			}
			
			if (i < length-1) {
				if (state.charAt(i) != state.charAt(i+1)) {
					count++;
					continue;
				}
			}
		}
		return count;
	}
}
