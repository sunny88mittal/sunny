package solution1400to1500;

public class Solution1423 {
	public int maxScore(int[] cardPoints, int k) {
		int n = cardPoints.length;
		int sumFromFront[] = new int[k + 1];
		int sumFromBack[] = new int[k + 1];

		for (int i = 0; i < k; i++) {
			sumFromFront[i + 1] = sumFromFront[i] + cardPoints[i];
			sumFromBack[i + 1] = sumFromBack[i] + cardPoints[n - i - 1];
		}

		int max = 0;
		for (int i = 0; i <= k; i++) {
			if (sumFromFront[i] + sumFromBack[k - i] > max) {
				max = sumFromFront[i] + sumFromBack[k - i];
			}
		}

		return max;
	}
}
