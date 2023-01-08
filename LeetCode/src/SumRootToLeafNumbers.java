public class SumRootToLeafNumbers {

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

	public int sumNumbers(TreeNode root) {
		return getSum(root, 0);
	}

	public int getSum(TreeNode root, int previousSum) {
		if (root.left == null && root.right == null) {
			return 10 * previousSum + root.val;
		}

		int leftSum = 0;
		int rightSum = 0;
		if (root.left != null) {
			leftSum = getSum(root.left, 10 * previousSum + root.val);
		}

		if (root.right != null) {
			rightSum = getSum(root.right, 10 * previousSum + root.val);
		}

		return leftSum + rightSum;
	}
}
