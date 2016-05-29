package SRM322;

public class DerivativeSequence {
	
	public int[] derSeq(int[] a, int n) {
		if ( n == 0) {
			return a;
		}
		int size = a.length-1;
		int [] seq = new int[size];
		for (int i=0; i<size; i++) {
			seq[i] = a[i+1] - a[i];
		}
		
		return derSeq(seq, n-1);
	}

}
