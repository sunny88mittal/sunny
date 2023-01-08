import java.util.ArrayList;
import java.util.List;

public class PathSum2 {

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

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		List<List<Integer>> paths = new ArrayList<List<Integer>>();
		if (root == null) {
			return paths;
		}

		if (root.val == targetSum && root.left == null && root.right == null) {
			List<Integer> path = new ArrayList<Integer>();
			path.add(targetSum);
			paths.add(path);
			return paths;
		}

		List<List<Integer>> leftPaths = pathSum(root.left, targetSum - root.val);
		List<List<Integer>> rightPaths = pathSum(root.right, targetSum - root.val);

		for (List<Integer> path : leftPaths) {
			path.add(0, root.val);
			paths.add(path);
		}

		for (List<Integer> path : rightPaths) {
			path.add(0, root.val);
			paths.add(path);
		}

		return paths;
	}
}
