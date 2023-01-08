public class LongestUnivaluePath {

	int longestPath = 0;

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public int longestUnivaluePath(TreeNode root) {
		longestPath(root);
		return longestPath;
	}

	public int longestPath(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftMax = longestPath(root.left);
		int rightMax = longestPath(root.right);

		int currentLeftPath = 0;
		int currentRightPath = 0;
		if (root.left != null && root.val == root.left.val) {
			currentLeftPath = leftMax + 1;
		}

		if (root.right != null && root.val == root.right.val) {
			currentRightPath = rightMax + 1;
		}

		// In final path we can sum both sides
		longestPath = Math.max(longestPath, currentLeftPath + currentRightPath);

		// Return only one side for intermediate path
		return Math.max(currentLeftPath, currentRightPath);
	}
}
