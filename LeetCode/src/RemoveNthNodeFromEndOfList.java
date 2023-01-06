
public class RemoveNthNodeFromEndOfList {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || head.next == null) {
			return null;
		}

		int count = 1;
		ListNode forwardPointer = head;
		while (count < n && forwardPointer != null) {
			forwardPointer = forwardPointer.next;
			++count;
		}

		// List finished, return null
		if (count < n) {
			return null;
		}

		while (forwardPointer.next.next != null) {
			
		}

		return head;
	}

	public static void main(String args[]) {
		RemoveNthNodeFromEndOfList obj = new RemoveNthNodeFromEndOfList();
		int[] nos = new int[] { 1, 2, 3, 4, 5 };
		ListNode rootNode = new ListNode(nos[0]);
		ListNode currentNode = rootNode;
		for (int i = 1; i < nos.length; i++) {
			ListNode node = new ListNode(nos[i]);
			currentNode.next = node;
			currentNode = node;
		}
		obj.removeNthFromEnd(rootNode, 2);
	}
}
