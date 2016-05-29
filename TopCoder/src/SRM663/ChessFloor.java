package SRM663;

public class ChessFloor {

	public int minimumChanges(String[] floor) {
		int min = Integer.MAX_VALUE;
		for (char b='a'; b<='z'; b++) {
			int diffb = getDiffCount(floor, b, false);
			for (char w='a'; w<='z'; w++) {
				if (b == w) {
					continue;
				}
				int diffw = getDiffCount(floor, w, true);
				int total = diffb + diffw;
				if (min > total) {
					min = total;
				}
			}	
		}
		return min;
	}
	
	private int getDiffCount(String[] floor, char c, boolean doXor) {
		int diff = 0;
		int n = floor.length;
		for (int i=0; i<n; i++) {
			String str = floor[i];
			int j = (i % 2);
			if (doXor) {
				j = j ^1;
			}
			while (j<n) {
				if (str.charAt(j) != c) {
					diff++;
				}
				j+=2;
			}
		}
		return diff;
	}
}
