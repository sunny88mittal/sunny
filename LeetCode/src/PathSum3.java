import java.util.LinkedList;
import java.util.Queue;

public class PathSum3 {

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

	public int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		int paths = 0;
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			paths += getPathSum(node, sum);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return paths;
	}

	private int getPathSum(TreeNode root, long sum) {
		if (root == null) {
			return 0;
		}

		int rootSum = 0;
		if (root.val == sum) {
			rootSum = 1;
		}

		int leftSum = getPathSum(root.left, sum - root.val);
		int rightSum = getPathSum(root.right, sum - root.val);
		return rootSum + leftSum + rightSum;
	}
}
