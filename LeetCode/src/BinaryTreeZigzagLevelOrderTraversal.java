import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

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

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> levels = new ArrayList<List<Integer>>();
		TreeNode separatorNode = new TreeNode();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		if (root != null) {
			queue.add(root);
			queue.add(separatorNode);
		}
		List<Integer> level = new ArrayList<Integer>();

		boolean flip = false;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == separatorNode) {
				if (flip) {
					Collections.reverse(level);
					flip = false;
				} else {
					flip = true;
				}
				levels.add(level);
				level = new ArrayList<Integer>();
				if (queue.isEmpty()) {
					break;
				}
				queue.add(separatorNode);
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

		return levels;
	}
}
