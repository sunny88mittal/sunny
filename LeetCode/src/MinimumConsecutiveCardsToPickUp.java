import java.util.HashMap;
import java.util.Map;

public class MinimumConsecutiveCardsToPickUp {

	public int minimumCardPickup(int[] cards) {
		Map<Integer, Integer> cardMap = new HashMap<>();
		int minimum = Integer.MAX_VALUE;
		for (int i = 0; i < cards.length; i++) {
			int card = cards[i];
			Integer prevLocation = cardMap.get(card);
			if (prevLocation != null) {
				int cardCount = i - prevLocation + 1;
				if (cardCount < minimum) {
					minimum = cardCount;
				}
			}
			cardMap.put(card, i);
		}

		return minimum == Integer.MAX_VALUE ? -1 : minimum;
	}
}
