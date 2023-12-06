package solution1300to1400;

import java.util.LinkedList;
import java.util.Queue;

public class Solution1302 {
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

	public int deepestLeavesSum(TreeNode root) {
		int sum = 0;
		TreeNode markerNode = new TreeNode();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(markerNode);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == markerNode && !queue.isEmpty()) {
				sum = 0;
				queue.add(markerNode);
			} else {
				sum += node.val;
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}

		return sum;
	}
}
