package solution200to300;

public class Solution235 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p.val > root.val && q.val > root.val) {
			return lowestCommonAncestor(root.right, p, q);
		}

		if (p.val < root.val && q.val < root.val) {
			return lowestCommonAncestor(root.left, p, q);
		}

		return root;
	}
}
