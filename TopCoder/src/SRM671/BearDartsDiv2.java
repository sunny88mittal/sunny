package SRM671;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BearDartsDiv2 {

	public long count(int[] w) {
        final int LIM = 1000 * 1000;
        long result = 0;
		int n = w.length;
		
        Map<Integer, List<Integer>> elemPos = new HashMap<Integer, List<Integer>>();
        for (int i=0; i<n; i++) {
        	List<Integer> positions = elemPos.get(w[i]);
        	if (positions == null) {
        	   positions = new ArrayList<Integer>();
        	   elemPos.put(w[i], positions);
        	}
        	positions.add(i);
        }
		
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				for (int k=j+1; k<n; k++) {
					long mul = (long) w[i] * w[j] * w[k];
					if (mul <= LIM) {
						List<Integer> positionsL = elemPos.get((int)mul);
						if (positionsL != null) {
							int size = positionsL.size();
							for (int p=0; p<size; p++) {
								if (positionsL.get(p) > k) {
									result += size-p;
									break;
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	public static void main (String args[]) {
		BearDartsDiv2 obj = new BearDartsDiv2();
		System.out.println(obj.count(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}));
	}
}
