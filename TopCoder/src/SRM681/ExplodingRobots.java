package SRM681;

public class ExplodingRobots {

	public String canExplode(int x1, int y1, int x2, int y2, String instructions) {
		int xdis = Math.abs(x2-x1);
		int ydis = Math.abs(y2-y1);
		
		int uc = getCount(instructions, 'U');
		int dc = getCount(instructions, 'D');
		int rc = getCount(instructions, 'R');
		int lc = getCount(instructions, 'L');
		
	    int xmov = lc + rc;
	    int ymov = uc + dc;
	    
	    if ((x1 == x2 && y1 == y2) || (xdis <= xmov && ydis <= ymov)) {
	    	return "Explosion";
	    }
		
		return "Safe";
	}
	
	private int getCount (String s, char c) {
		int count = 0;
		for (char ch : s.toCharArray()) {
		    if (ch == c ){
		    	count++;
		    }
		}
		return count;
	}
}
