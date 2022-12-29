import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ZigZagConversion {

	public String convert(String s, int numRows) {
		Map<Integer, Queue<Character>> lineQueue = new HashMap<Integer, Queue<Character>>();
		for (int i = 0; i < numRows; i++) {
			lineQueue.put(i, new LinkedList<Character>());
		}

		int i = 0;
		while (i < s.length()) {
			for (int j = 0; j < numRows && i < s.length(); j++) {
				lineQueue.get(j).add(s.charAt(i));
				++i;
			}

			for (int j = numRows - 2; j >= 1 && i < s.length(); j--) {
				lineQueue.get(j).add(s.charAt(i));
				++i;
			}
		}

		String finalString = "";
		for (int j = 0; j < numRows; j++) {
			Queue<Character> line = lineQueue.get(j);
			while (!line.isEmpty()) {
				finalString += line.remove();
			}
		}

		return finalString;
	}
}
