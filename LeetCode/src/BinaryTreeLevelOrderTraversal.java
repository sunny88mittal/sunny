import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

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

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> traversal = new ArrayList<List<Integer>>();
		TreeNode markerNode = new TreeNode();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root != null) {
			queue.add(root);
			queue.add(markerNode);
		}
		List<Integer> level = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == markerNode) {
				traversal.add(level);
				level = new ArrayList<Integer>();
				if (!queue.isEmpty()) {
					queue.add(markerNode);
				}
				continue;
			}

			level.add(node.val);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}

		return traversal;
	}
}
