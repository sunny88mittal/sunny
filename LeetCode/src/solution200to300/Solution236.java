package solution200to300;

public class Solution236 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == root || q == root) {
			return root;
		}
		TreeNode lp = lowestCommonAncestor(root.left, p, q);
		TreeNode rp = lowestCommonAncestor(root.right, p, q);

		if (lp != null && rp != null) {
			return root;
		}

		return lp == null ? rp : lp;
	}
}
