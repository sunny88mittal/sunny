package solution700to800;

public class Solution718 {
	/*public int findLength(int[] nums1, int[] nums2) {
		int maxLength = 0;
		Map<Integer, List<Integer>> nums2Map = new HashMap<>();
		for (int i = 0; i < nums2.length; i++) {
			List<Integer> positions = nums2Map.get(nums2[i]);
			if (positions == null) {
				positions = new ArrayList<>();
			}
			positions.add(i);
			nums2Map.put(nums2[i], positions);
		}

		for (int i = 0; i < nums1.length; i++) {
			List<Integer> positions = nums2Map.get(nums1[i]);
			if (positions == null) {
				positions = new ArrayList<>();
			}

			for (int j = 0; j < positions.size(); j++) {
				int cMaxLength = 0;
				int m = i;
				int n = positions.get(j);
				while (m < nums1.length && n < nums2.length && nums1[m] == nums2[n]) {
					++cMaxLength;
					++m;
					++n;
				}

				if (cMaxLength > maxLength) {
					maxLength = cMaxLength;
				}
			}
		}

		return maxLength;
	}*/
	
	
	public int findLength(int[] nums1, int[] nums2) {
		final int m = nums1.length;
		final int n = nums2.length;
		int ans = 0;
		// dp[i][j] := the maximum length of a subarray that appears in both
		// nums1[i..m) and nums2[j..n)
		int[][] dp = new int[m + 1][n + 1];

		for (int i = m - 1; i >= 0; --i)
			for (int j = n - 1; j >= 0; --j)
				if (nums1[i] == nums2[j]) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
					ans = Math.max(ans, dp[i][j]);
				}

		return ans;
	}
		

	public static void main(String args[]) {
		Solution718 obj = new Solution718();
		int nums1[] = new int[] { 5, 14, 53, 80, 48 };
		int nums2[] = new int[] { 50, 47, 3, 80, 83 };
		obj.findLength(nums1, nums2);
	}
}
