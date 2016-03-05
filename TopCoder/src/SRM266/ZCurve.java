package SRM266;

public class ZCurve {

	public int zValue(int N, int r, int c) {
		if (N == 1) {
			return r * 2 + c;
		}

		int tn = N - 1;
		int value = 0;
		int tn2 = (int) Math.pow(2, tn);
		if (r > tn2 - 1 && c > tn2 - 1) {
			value += 3 * tn2 * tn2 + zValue(tn, r - tn2, c - tn2);
		} else if (r > tn2 - 1 && c <= tn2 - 1) {
			value += 2 * tn2 * tn2 + zValue(tn, r - tn2, c);
		} else if (r <= tn2 - 1 && c > tn2 - 1) {
			value += tn2 * tn2 + zValue(tn, r, c - tn2);
		} else {
			value += zValue(tn, r, c);
		}

		return value;
	}
}