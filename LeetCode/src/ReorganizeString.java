import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class ReorganizeString {

	private class CharacterTuple implements Comparable<CharacterTuple> {
		char c;
		int count;

		public CharacterTuple(char c, int count) {
			this.c = c;
			this.count = count;
		}

		@Override
		public int compareTo(CharacterTuple arg0) {
			if (this.count > arg0.count) {
				return -1;
			} else if (this.count == arg0.count) {
				if (this.c > arg0.c) {
					return -1;
				}
			}

			return 1;
		}
	}

	public String reorganizeString(String s) {
		char[] chars = s.toCharArray();

		// Get Count
		Map<Character, Integer> charCount = new HashMap<Character, Integer>();
		for (char c : chars) {
			Integer counter = charCount.get(c);
			if (counter == null) {
				counter = Integer.valueOf(0);
			}
			counter += 1;
			charCount.put(c, counter);
		}

		// Create Priority Queue
		PriorityQueue<CharacterTuple> pq = new PriorityQueue<CharacterTuple>();
		for (Entry<Character, Integer> entry : charCount.entrySet()) {
			CharacterTuple tuple = new CharacterTuple(entry.getKey(), entry.getValue());
			pq.add(tuple);
		}

		// Create the new string
		String finalString = "";
		while (!pq.isEmpty()) {
			CharacterTuple tuple1 = pq.poll();
			CharacterTuple tuple2 = pq.poll();
			finalString = processTuple(pq, finalString, tuple1);
			finalString = processTuple(pq, finalString, tuple2);
		}

		// Check for same adjacent characters
		for (int i = 0; i < finalString.length() - 1; i++) {
			if (finalString.charAt(i) == finalString.charAt(i + 1)) {
				return "";
			}
		}

		return finalString;
	}

	private String processTuple(PriorityQueue<CharacterTuple> pq, String s, CharacterTuple tuple) {
		if (tuple != null) {
			s += tuple.c;
			tuple.count -= 1;
			if (tuple.count > 0) {
				pq.add(tuple);
			}
		}
		return s;
	}

	public static void main(String args[]) {
		ReorganizeString obj = new ReorganizeString();
		obj.reorganizeString("aaabbbcccddd");
	}
}
