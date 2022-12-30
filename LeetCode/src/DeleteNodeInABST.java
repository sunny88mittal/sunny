
public class DeleteNodeInABST {

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

	public TreeNode deleteNode(TreeNode root, int key) {
		// Base case
		if (root == null) {
			return root;
		}

		if (key < root.val) { // If key is < root search on left
			root.left = deleteNode(root.left, key);
		} else if (key > root.val) { // If key is > root search on right
			root.right = deleteNode(root.right, key);
		} else {
			// If only right child return that
			if (root.left == null) {
				return root.right;
			}
			// If only left child return that
			if (root.right == null) {
				return root.left;
			}

			// Get in-order successor and put it at current node
			root.val = getMin(root.right);
			// Delete the in-order successor
			root.right = deleteNode(root.right, root.val);
		}

		return root;
	}

	private int getMin(TreeNode root) {
		int min = root.val;
		while (root.left != null) {
			min = root.left.val;
			root = root.left;
		}
		return min;
	}
}
