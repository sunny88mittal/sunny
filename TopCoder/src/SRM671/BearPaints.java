package SRM671;

public class BearPaints {

	public long maxArea(int W, int H, long M) {
		long res = 1;
		for (int i=1; i<=W; i++) {
			res = Math.max(res, i * Math.min(M/i, (long)H));
		}
		return res;
	}
	
	public static void main (String args[]) {
		BearPaints obj = new BearPaints();
		System.out.println(obj.maxArea(3, 5, 14));
		System.out.println(obj.maxArea(4, 4, 10));
		System.out.println(obj.maxArea(1000000, 12345, 1000000000000L));
		System.out.println(obj.maxArea(1000000, 1000000, 720000000007L));
		System.out.println(obj.maxArea(1000000, 1000000, 999999999999L));
	}
}
