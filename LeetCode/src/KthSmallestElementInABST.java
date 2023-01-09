
public class KthSmallestElementInABST {

	private int kThSmallest = 0;

	private int count = 0;

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

	public int kthSmallest(TreeNode root, int k) {
		InOrderTraversal(root, k);
		return kThSmallest;
	}

	public void InOrderTraversal(TreeNode root, int k) {
		if (root != null) {
			InOrderTraversal(root.left, k);
			++count;
			if (count == k) {
				kThSmallest = root.val;
				return;
			}
			InOrderTraversal(root.right, k);
		}
	}
}
