package SRM659;

import java.util.Arrays;

//Slightly simple solution at http://apps.topcoder.com/wiki/display/tc/SRM+659
public class FilipTheFrog {

	public int countReachableIslands(int[] positions, int L) {
		int ans = 1;
		int startPositionVal = positions[0];
		int n = positions.length;
		
		Arrays.sort(positions);
		
		int startPosition = -1;
		for (int i=0; i<n; i++) {
			if (positions[i] == startPositionVal) {
				startPosition = i;
				break;
			}
		}
		
		int tstart = startPosition;
		for (int i=startPosition-1; i>=0; i--) {
			if (positions[tstart] - positions[i] > L) {
				break;
			}
			ans++;
			tstart--;
		}
		
		tstart = startPosition;
		for (int i=startPosition + 1; i<n; i++) {
			if (positions[i] - positions[tstart] > L) {
				break;
			}
			ans++;
			tstart++;
		}
		
		return ans;
	}
}
