package solution1000to1100;

public class Solution1038 {
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

	int globalValue = 0;

	public TreeNode bstToGst(TreeNode root) {
		if (root != null) {
			bstToGst(root.right);
			root.val += globalValue;
			globalValue = root.val;
			bstToGst(root.left);
		}
		return root;
	}
}
