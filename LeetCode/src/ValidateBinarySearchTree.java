import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.NodeSetData;

public class ValidateBinarySearchTree {

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

	public boolean isValidBST(TreeNode root) {
		List<Integer> nodes = new ArrayList<Integer>();
		inOrderTraversal(root, nodes);
		for (int i = 0; i < nodes.size() - 1; i++) {
			if (nodes.get(i) >= nodes.get(i + 1)) {
				return false;
			}
		}

		return true;
	}

	private void inOrderTraversal(TreeNode root, List<Integer> nodes) {
		if (root != null) {
			inOrderTraversal(root.left, nodes);
			nodes.add(root.val);
			inOrderTraversal(root.right, nodes);
		}
	}
}
