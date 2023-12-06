package solution1100to1200;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution1162 {

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

	public long kthLargestLevelSum(TreeNode root, int k) {
		TreeNode sentinelNode = new TreeNode();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(sentinelNode);

		long currentLevelSum = 0;
		PriorityQueue<Long> pq = new PriorityQueue<>();

		while (!queue.isEmpty()) {
			TreeNode node = queue.remove();
			if (node == sentinelNode) {
				pq.add(currentLevelSum);
				if (pq.size() > k) {
					pq.poll();
				}
				currentLevelSum = 0;
				if (!queue.isEmpty()) {
					queue.add(sentinelNode);
				}
			} else {
				currentLevelSum += (long) node.val;
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}

		if (pq.size() < k) {
			return -1;
		}
		return (long) pq.toArray()[0];
	}
}
