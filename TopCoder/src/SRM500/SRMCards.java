package SRM500;

import java.util.Arrays;

public class SRMCards {

	public int maxTurns(int[] cards) {
		int maxTurns = 0;
		Arrays.sort(cards);
		for (int i=0; i<cards.length; i++) {
			int card = cards[i];
			maxTurns++;
			if (i+1 < cards.length) {
				if (cards[i+1] == card +1){
					i++;
				}	
			}
		}
		return maxTurns;
	}
}
