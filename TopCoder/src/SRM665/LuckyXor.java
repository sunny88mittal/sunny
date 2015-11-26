package SRM665;

public class LuckyXor {

	public int construct(int a) {
		for (int i=a+1; i<=100; i++) {
			int xor = i^a;
			if (xor!=0 && isLucky(xor)) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean isLucky(int n) {
		while (n !=0) {
			int rem = n % 10;
			if (rem == 4 || rem == 7) {
				n = n / 10;
				continue;
			}
			return false;
		}
		return true;
	}
	
	public static void main (String args[]) {
		LuckyXor obj = new LuckyXor();
		System.out.println(obj.construct(4));
		System.out.println(obj.construct(19));
		System.out.println(obj.construct(88));
		System.out.println(obj.construct(36));
	}
}
