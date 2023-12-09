package solution2300to2400;

public class Solution2391 {
	public int garbageCollection(String[] garbage, int[] travel) {
		for (int i = 1; i < travel.length; i++) {
			travel[i] = travel[i] + travel[i - 1];
		}

		int me = -1;
		int ge = -1;
		int pe = -1;
		int mc = 0;
		int pc = 0;
		int gc = 0;

		int totalTime = 0;

		for (int i = 0; i < garbage.length; i++) {
			String iGarbage = garbage[i];
			for (char ch : iGarbage.toCharArray()) {
				if (ch == 'P') {
					++pc;
					if (i > pe) {
						pe = i;
					}
				} else if (ch == 'M') {
					++mc;
					if (i > me) {
						me = i;
					}

				} else if (ch == 'G') {
					++gc;
					if (i > ge) {
						ge = i;
					}
				}
			}
		}

		if (pc > 0) {
			totalTime += pc + (pe > 0 ? travel[pe - 1] : 0);
		}

		if (mc > 0) {
			totalTime += mc + (me > 0 ? travel[me - 1] : 0);
		}

		if (gc > 0) {
			totalTime += gc + (ge > 0 ? travel[ge - 1] : 0);
		}

		return totalTime;
	}
}
