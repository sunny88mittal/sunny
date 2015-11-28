package SRM669;

import java.util.Arrays;

public class CombiningSlimes {

	public int maxMascots(int[] a) {
		Arrays.sort(a);
		int sum = 0;
		int n = a.length;
		for (int i=0; i<n-1; i++) {
			sum += a[i] * a[n-1];
			a[n-1] = a[i] + a[n-1];
		}
		return sum;
	}
	
	public static void main (String args[]) {
		CombiningSlimes obj = new CombiningSlimes();
		System.out.println(obj.maxMascots(new int[]{3,4}));
		System.out.println(obj.maxMascots(new int[]{2,2,2}));
		System.out.println(obj.maxMascots(new int[]{1,2,3}));
		System.out.println(obj.maxMascots(new int[]{3,1,2}));
		System.out.println(obj.maxMascots(new int[]{7,6,5,3,4,6}));
	}
}
