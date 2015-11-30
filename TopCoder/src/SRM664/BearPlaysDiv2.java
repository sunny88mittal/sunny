package SRM664;

public class BearPlaysDiv2 {

	boolean[][] can = new boolean[1501][1501];
	
	public String equalPiles(int A, int B, int C) {
		int[] t = new int[]{A,B,C};
		int S = A+B+C; 
		if (S %3 ==0) {
			rec(t);
			if (can[S/3][S/3])return "possible";
		}
		return "impossible";
	}
	
	public void rec(int[] t) {
		if (can[t[0]][t[1]]) return;
		can[t[0]][t[1]] = true;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (t[i] < t[j]) {
					int[] t1 = new int[] {2*t[i], t[j]-t[i], t[3-i-j]};
					rec(t1);
				}
			}
		}
	}
	
	public static void main (String args[]) {
		BearPlaysDiv2 obj = new BearPlaysDiv2();
		System.out.println(obj.equalPiles(10,15,35));
		System.out.println(obj.equalPiles(1,1,2));
		System.out.println(obj.equalPiles(4,6,8));
		System.out.println(obj.equalPiles(18,18,18));
		System.out.println(obj.equalPiles(225,500,475));
	}
}