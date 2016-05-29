package week6;

import java.math.BigInteger;

public class FactoringChallenege4 {
	
	 public static void main(String [] args){
	   	  BigInteger N = new BigInteger("179769313486231590772930519078902473361797697894230657273430081157732675805505620686985379449212982959585501387537164015710139858647833778606925583497541085196591615128057575940752635007475935288710823649949940771895617054361149474865046711015101563940680527540071584560878577663743040086340742855278549092581");
	   	  BigInteger A = bigIntSqRootCeil(N);
	   	  BigInteger x = bigIntSqRootCeil(A.pow(2).subtract(N));
	   	  BigInteger p = A.subtract(x);
	   	  BigInteger q = A.add(x);
	   	  
	   	  BigInteger phin = (p.subtract(new BigInteger(1 + ""))).multiply(q.subtract(new BigInteger(1 + "")));
	   	  BigInteger e = new BigInteger("65537");
	   	  BigInteger d = e.modInverse(phin);
	   	  
	   	  
	   	  BigInteger C = new BigInteger("22096451867410381776306561134883418017410069787892831071731839143676135600120538004282329650473509424343946219751512256465839967942889460764542040581564748988013734864120452325229320176487916666402997509188729971690526083222067771600019329260870009579993724077458967773697817571267229951148662959627934791540");
	   	  BigInteger D = C.modPow(d, N);
	   	  
	   	  String decryptStr = D.toString(16);
	   	  decryptStr = "0" + decryptStr;
	   	  
	   	  String messageStr = decryptStr.split("00")[1];
	   	  printASCIIMSg(messageStr);
	 }
	 
	 public static void printASCIIMSg(String hexStr) {
		 int length = hexStr.length();
		 String message = "";
		 for (int i=0; i<length/2; i++) {
			 String nextCharStr = hexStr.substring(i*2, i*2+2);
			 int intValue = Integer.valueOf(nextCharStr, 16);
			 char cValue = (char) intValue;
			 message += cValue;
		 }
		 System.out.println(message);
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
