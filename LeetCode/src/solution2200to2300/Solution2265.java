package solution2200to2300;

public class Solution2265 {

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

	public int averageOfSubtree(TreeNode root) {
		return compRes(root)[2];
	}

	public int[] compRes(TreeNode root) {
		if (root == null) {
			return new int[] { 0, 0, 0 };
		}

		int[] leftRes = compRes(root.left);
		int[] rightRes = compRes(root.right);

		int sum = root.val + leftRes[0] + rightRes[0];
		int count = 1 + leftRes[1] + rightRes[1];
		int averageNodes = leftRes[2] + rightRes[2] + (sum / count == root.val ? 1 : 0);

		return new int[] { sum, count, averageNodes };
	}
}
