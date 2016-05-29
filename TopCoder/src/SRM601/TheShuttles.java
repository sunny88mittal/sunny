package SRM601;

public class TheShuttles {
	
	public int getLeastCost(int[] cnt, int baseCost, int seatCost) {
	    int totalCost = 0;
	    int maxEmply = -1;
	    for (int i=0; i<cnt.length; i++) {
	    	if (cnt[i] > maxEmply) {
	    		maxEmply = cnt[i];
	    	}
	    }
	    
	    totalCost  = totalCost +  cnt.length * (baseCost + maxEmply * seatCost);
	    return totalCost;
	}
}
