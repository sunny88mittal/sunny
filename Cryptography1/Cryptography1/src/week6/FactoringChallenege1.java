package week6;

import java.math.BigInteger;

public class FactoringChallenege1 {
	
	 public static void main(String [] args){
	   	  BigInteger N = new BigInteger("179769313486231590772930519078902473361797697894230657273430081157732675805505620686985379449212982959585501387537164015710139858647833778606925583497541085196591615128057575940752635007475935288710823649949940771895617054361149474865046711015101563940680527540071584560878577663743040086340742855278549092581");
	   	  BigInteger A = bigIntSqRootCeil(N);
	   	  BigInteger x = bigIntSqRootCeil(A.pow(2).subtract(N));
	   	  System.out.println("p is:" + A.subtract(x));
	   	  System.out.println("q is:" + A.add(x));
	 }
	 
	 public static BigInteger bigIntSqRootCeil(BigInteger x)
		        throws IllegalArgumentException {
		    if (x.compareTo(BigInteger.ZERO) < 0) {
		        throw new IllegalArgumentException("Negative argument.");
		    }
		    // square roots of 0 and 1 are trivial and
		    // y == 0 will cause a divide-by-zero exception
		    if (x == BigInteger.ZERO || x == BigInteger.ONE) {
		        return x;
		    } // end if
		    BigInteger two = BigInteger.valueOf(2L);
		    BigInteger y;
		    // starting with y = x / 2 avoids magnitude issues with x squared
		    for (y = x.divide(two);
		            y.compareTo(x.divide(y)) > 0;
		            y = ((x.divide(y)).add(y)).divide(two));
		    if (x.compareTo(y.multiply(y)) == 0) {
		        return y;
		    } else {
		        return y.add(BigInteger.ONE);
		    }
	}
}
