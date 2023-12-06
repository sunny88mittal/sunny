package solution2500to2600;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2583 {

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

	public int maxLevelSum(TreeNode root) {
		int maxSum = Integer.MIN_VALUE;
		int maxLevel = -1;
		TreeNode sentinelNode = new TreeNode();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(sentinelNode);

		int currentLevel = 1;
		int currentLevelSum = 0;

		while (!queue.isEmpty()) {
			TreeNode node = queue.remove();
			if (node == sentinelNode) {
				if (currentLevelSum > maxSum) {
					maxSum = currentLevelSum;
					maxLevel = currentLevel;
				}
				++currentLevel;
				currentLevelSum = 0;
				if (!queue.isEmpty()) {
					queue.add(sentinelNode);
				}
			} else {
				currentLevelSum += node.val;
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}

		return maxLevel;
	}
}
