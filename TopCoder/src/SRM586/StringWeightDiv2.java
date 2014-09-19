package SRM586;

import java.math.BigInteger;

public class StringWeightDiv2 {
	
	public static void main (String args[]) {
		StringWeightDiv2 obj = new StringWeightDiv2();
		System.out.println(obj.countMinimums(50));
	}
	
	public int countMinimums(int L) {
		if (L<=26) {
			BigInteger permutes = get26PL(L);
			return (permutes.divideAndRemainder(new BigInteger("1000000009")))[1].intValue();
		}
		
		//After Counting it comes to L*(L-26)*26!
	    BigInteger ans = get26PL(26);
	    ans = ans.multiply(new BigInteger("26")).multiply(new BigInteger("" + (L-26)));
	    return ans.divideAndRemainder(new BigInteger("1000000009"))[1].intValue();
		
	}
	
	public BigInteger get26PL(int L) {
		BigInteger comb = new BigInteger("1");	
		if (L>=26) {
			L=26;
		}
		for (int i=0; i<L;i++) {
			comb = comb.multiply(new BigInteger("" +  (26-i)));
		}
		
		return comb;
	}
	
}
