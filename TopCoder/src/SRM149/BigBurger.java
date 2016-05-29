package SRM149;

public class BigBurger {
	
	public int maxWait(int[] arrival, int[] service) {
		int length = arrival.length;
		int maxWait = 0;
		int clockTime = arrival[0] + service[0];
		for (int i=1; i<length; i++){
			int arrivalTime = arrival[i];
			if (arrivalTime < clockTime) {
				int waitTime = clockTime - arrivalTime;
				if (waitTime > maxWait){
					maxWait = waitTime;
				}
				clockTime = clockTime + service[i];
			} else {
				clockTime = arrival[i] + service[i];
			}
		}
		return maxWait;
	}

}
