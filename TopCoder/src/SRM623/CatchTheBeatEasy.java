package SRM623;

public class CatchTheBeatEasy {

	static final String ABLE_TO_CATCH = "Able to catch";

    static final String NOT_ABLE_TO_CATCH = "Not able to catch";

	public String ableToCatchAll(int[] x, int[] y) {
		int currentPosition = 0;
		int currentTime = 0;
		boolean[] done = new boolean[x.length];
		int count = 0;
		while (count < x.length) {
			int toConsume = -1;
			for (int i = 0; i < x.length; i++) {
				if (!done[i]) {
					if (toConsume == -1 
							|| (y[i] < y[toConsume])
							|| (y[i] == y[toConsume] && Math.abs(currentPosition- x[i]) < Math.abs(currentPosition- x[toConsume]))) {
						toConsume = i;
					}
				}
			}
			int toTravel = Math.abs(currentPosition - x[toConsume]);
			int inTime = y[toConsume] - currentTime;
			if (toTravel > inTime) {
				return NOT_ABLE_TO_CATCH;
			} else {
				currentPosition = x[toConsume];
				currentTime = currentTime + inTime;
				done[toConsume] = true;
				count++;
			}
		}
		return ABLE_TO_CATCH;
	}
}
