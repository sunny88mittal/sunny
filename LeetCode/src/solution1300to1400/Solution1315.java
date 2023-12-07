package solution1300to1400;

public class Solution1315 {

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

	public int sumEvenGrandparent(TreeNode root) {
		int[] parents = new int[] { -1, -1 };
		return sumEvenGrandparent(root, parents);
	}

	public int sumEvenGrandparent(TreeNode root, int[] parents) {
		int sum = 0;
		if (root != null) {
			sum = parents[1] % 2 == 0 ? root.val : 0;
			int[] newParents = new int[] { root.val, parents[0] };
			sum += sumEvenGrandparent(root.left, newParents);
			sum += sumEvenGrandparent(root.right, newParents);
		}
		return sum;
	}
}
