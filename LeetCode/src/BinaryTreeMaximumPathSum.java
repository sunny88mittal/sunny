
public class BinaryTreeMaximumPathSum {

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

	private int maxSum = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		getMaxPathSum(root);
		return maxSum;
	}

	public int getMaxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftMax = Math.max(getMaxPathSum(root.left), 0);
		int rightMax = Math.max(getMaxPathSum(root.right), 0);

		maxSum = Math.max(maxSum, leftMax + rightMax + root.val);

		return Math.max(leftMax + root.val, rightMax + root.val);
	}
}
