package SRM664;

public class BearCheats {

	public String eyesight(int A, int B) {
		int diffs = 0;
		while (A != 0) {
			if (A % 10 != B % 10) {
				diffs++;
			}
			A = A / 10;
			B = B / 10;
		}

		return diffs > 1 ? "glasses" : "happy";
	}
}
