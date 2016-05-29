package SRM586;

public class TeamsSelection {
		
	public String simulate(int[] preference1, int[] preference2) {
	    String result = "";
	    int length = preference1.length;
	    int prefrences[] = new int[length];
	    int p1 = 0;
	    int p2 = 0;
	    int i=0;
	    while (true) {
	    	while (true) {
	    		int pref1 = preference1[p1] - 1;
		    	if (prefrences[pref1] == 0) {
		    		prefrences[pref1] = 1;
		    		i++;
		    		break;
		    	}
		    	p1++;
	    	}
	    	
	    	if (i == length) {
	    		break;
	    	}
	    	
	    	while (true) {
	    		int pref2 = preference2[p2] - 1;
		    	if (prefrences[pref2] == 0) {
		    		prefrences[pref2] = 2;
		    		i++;
		    		break;
		    	}
		    	p2++;
	    	}
	    	
	    	if (i == length) {
	    		break;
	    	}
	    }
	    
	    for (i=0; i<length; i++){
	    	result += prefrences[i];
	    }
		
		return result;
	}
}
