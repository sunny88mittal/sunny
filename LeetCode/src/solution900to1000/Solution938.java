package solution900to1000;

public class Solution938 {
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

	public int rangeSumBST(TreeNode root, int low, int high) {
		int sum = 0;
		if (root == null) {
			return sum;
		}
		if (root.val >= low && root.val <= high) {
			sum += root.val;
		}

		return sum + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
	}
}
