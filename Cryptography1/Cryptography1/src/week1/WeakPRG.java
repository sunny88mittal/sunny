package week1;

public class WeakPRG {
	
	public static void main (String[] args) {
		int P =  295075153;
		int[] outputs = new int[] {210205973,
                 22795300,
				 58776750,
				 121262470,
				 264731963,
				 140842553,
				 242590528,
				 195244728,
				 86752752};
		 int y = 0;
		 for (int x=0; x<=P; x++) {
			 y = outputs[0]^x;
			 int j;
			 int nexpx = (2*x + 5) %  P;
			 int nexpy = (3*y + 7) %  P;
			 for (j=1; j<outputs.length; j++) {   
			    if ((nexpx^nexpy) != outputs[j]) {
			    	break;
			    }
			    nexpx = (2*nexpx + 5) %  P;
				nexpy = (3*nexpy + 7) %  P;    
			 }
			 if (j == outputs.length) {
				 System.out.println("X:" + x);
				 System.out.println("Y:" + y);
				 System.out.println("Next PRG is:" + (nexpx^nexpy));
				 break;
			 }
		 }
	}
}
