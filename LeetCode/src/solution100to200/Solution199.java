package solution100to200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution199 {
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

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> rightView = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode sentinelNode = new TreeNode();
		if (root != null) {
			queue.add(root);
			queue.add(sentinelNode);
		}

		while (!queue.isEmpty()) {
			TreeNode node = queue.remove();
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}

			if (queue.peek() == sentinelNode) {
				rightView.add(node.val);
				queue.remove();
				if (!queue.isEmpty()) {
					queue.add(sentinelNode);
				}
			}
		}

		return rightView;
	}
}
