package SRM669;

import java.util.HashMap;
import java.util.Map;

public class LiveConcert {

	public int maxHappiness(int[] h, String[] s) {
		int result = 0;
		
		Map<String, Integer> singerMax = new HashMap<String, Integer>();
		int n = h.length;
		for (int i=0; i<n; i++) {
		   int happiness = h[i];
		   if (singerMax.get(s[i]) != null) {
			   happiness = singerMax.get(s[i]) > happiness ? singerMax.get(s[i]) : happiness;
		   }
		   singerMax.put(s[i], happiness);
		}
		
		
		for (Integer happiness : singerMax.values()) {
			result += happiness;
		}
		
		return result;
	}
}
