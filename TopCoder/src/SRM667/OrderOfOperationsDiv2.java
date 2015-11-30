package SRM667;

public class OrderOfOperationsDiv2 {

	public int minTime(String[] s) {
		int n = s.length;
		int m = s[0].length();
		int count = 0;
		int executec = 0;
		boolean[] accesed = new boolean[m];
		boolean[] executed = new boolean[n];

		while (executec < n) {
			int min = -1;
			int minc = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (!executed[i]) {
					int tcount = 0;
					char[] ch = s[i].toCharArray();
					for (int j = 0; j < m; j++) {
						if (ch[j] == '1' && !accesed[j]) {
							tcount++;
						}
					}
					if (tcount < minc) {
						min = i;
						minc = tcount;
					}
				}
			}

			char[] ch = s[min].toCharArray();
			for (int j = 0; j < m; j++) {
				if (ch[j] == '1' && !accesed[j]) {
					accesed[j] = true;
				}
			}

			executed[min] = true;
			count += minc * minc;
			executec++;
		}

		return count;
	}

	public static void main(String args[]) {
		OrderOfOperationsDiv2 obj = new OrderOfOperationsDiv2();
		System.out.println(obj.minTime(new String[] { "111", "001", "010" }));
		System.out.println(obj.minTime(new String[] { "11101", "00111",
				"10101", "00000", "11000" }));
		System.out.println(obj.minTime(new String[]

		{ "11111111111111111111" }));
		System.out.println(obj.minTime(new String[]

		{ "1000", "1100", "1110" }));

		System.out.println(obj.minTime(new String[]

		{ "111", "111", "110", "100" }));
	}

}
