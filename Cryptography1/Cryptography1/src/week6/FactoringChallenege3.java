package week6;

import java.math.BigInteger;

public class FactoringChallenege3 {
	
	 public static void main(String [] args){
	   	  BigInteger N = new BigInteger("720062263747350425279564435525583738338084451473999841826653057981916355690188337790423408664187663938485175264994017897083524079135686877441155132015188279331812309091996246361896836573643119174094961348524639707885238799396839230364676670221627018353299443241192173812729276147530748597302192751375739387929");
	   	  BigInteger N24  = N.multiply(new BigInteger(24 + ""));
	   	  BigInteger A = bigIntSqRootCeil(N24);
	   	  BigInteger x = bigIntSqRootCeil(A.pow(2).subtract(N24));
	   	  BigInteger p = A.subtract(x).divide(new BigInteger(6 + ""));
	   	  BigInteger q = A.add(x).divide(new BigInteger(4 + "")); 
	   	  System.out.println("p is:" + p);
	   	  System.out.println("q is:" + q);
	 }
	 
	 public static BigInteger bigIntSqRootFloor(BigInteger x)
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
		    return y;
	} // end bigIntSqRootFloor
	 
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
