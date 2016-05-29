package adobe;

class Node {
	int value;
	
	Node left;
	
	Node right;
}

public class SumTree {

	public static boolean hasSum( Node root, int sum ) {
		if (root == null) {
			return false;
		}
		
		int value =  root.value;
		if (value == sum) {
			return true;
		}
		
		return hasSum(root.left, sum - value) || hasSum(root.right, sum - value);
	}
	
	public static void main (String args[]) {
		Node root = new Node();
		root.value = 28;
		
		Node node1 = new Node();
		node1.value = 99;
		
		Node node2 = new Node();
		node2.value = 0;
		
		root.left = node1;
		root.right = node2;
		
		Node node3 = new Node();
		node3.value = 11;
		
		Node node4 = new Node();
		node4.value = 67;
		
		node1.left = node3;
		node1.right = node4;
		
		Node node5 = new Node();
		node5.value = -5;
		
		node2.right = node5;
		
		Node node6 = new Node();
		node6.value = -45;
		
		node3.right = node6;
		
		Node node7 = new Node();
		node7.value = 10;
		
		node5.left = node7;
		
		System.out.println(hasSum(root, 93));
		System.out.println(hasSum(root, 28));
		System.out.println(hasSum(root, 5));
		System.out.println(hasSum(root, 194));
		System.out.println(hasSum(root, 23));
		System.out.println(hasSum(root, 190));
		System.out.println(hasSum(root, 33));
	}
}
