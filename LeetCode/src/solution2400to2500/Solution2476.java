package solution2400to2500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2476 {

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

	public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> inOrder = new ArrayList<>();
		inOrderTraversal(root, inOrder);
		Integer[] arr = inOrder.toArray(new Integer[0]);
		for (int query : queries) {
			List<Integer> r = new ArrayList<>();
			int location = Arrays.binarySearch(arr, query);
			if (location >= 0) {
				r.add(query);
				r.add(query);
			} else {
				location = -1 * (location + 1);
				if (location - 1 >= 0) {
					r.add(arr[location - 1]);
				} else {
					r.add(-1);
				}

				if (location < arr.length) {
					r.add(arr[location]);
				} else {
					r.add(-1);
				}
			}
			result.add(r);
		}
		return result;
	}

	public void inOrderTraversal(TreeNode root, List<Integer> inOrder) {
		if (root != null) {
			inOrderTraversal(root.left, inOrder);
			inOrder.add(root.val);
			inOrderTraversal(root.right, inOrder);
		}
	}
}
