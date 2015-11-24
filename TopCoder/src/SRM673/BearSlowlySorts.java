package SRM673;

public class BearSlowlySorts {

	public int minMoves(int[] w) {
	   if (isInAscOrder(w)) {
		   return 0;
	   }
	   
	   int min = w[0];
	   for (int i : w) {
		   if (i < min) {
			   min = i;
		   }
	   }
	   
	   int max = w[0];
	   for (int i : w) {
		   if (i > max) {
			   max = i;
		   }
	   }
	   
	   if (min == w[0] || max == w[w.length-1]) {
		   return 1;
	   }
	   
	   if(min == w[w.length - 1] && max == w[0]) {
		   return 3;
	   }
	   
	   return 2;
	}
	
	public boolean isInAscOrder(int [] w) {
		int curr = w[0];
		for (int i=1; i<w.length; i++) {
			if (w[i] < curr) {
				return false;
			}
			curr = w[i];
		}
		return true;
	}
	
	public static void main (String args[]) {
		BearSlowlySorts obj = new BearSlowlySorts();
		System.out.println(obj.minMoves(new int[] {2,6,8,5}));
		System.out.println(obj.minMoves(new int[] {4,3,1,6,2,5}));
		System.out.println(obj.minMoves(new int[] {93,155,178,205,213,242,299,307,455,470,514,549,581,617,677}));
		System.out.println(obj.minMoves(new int[] {50,20,30,40,10}));
		System.out.println(obj.minMoves(new int[] {234,462,715,596,906,231,278,223,767,925,9,526,369,319,241,354,317,880,5,696}));
	}
}