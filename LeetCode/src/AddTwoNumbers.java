public class AddTwoNumbers {

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

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode sumList = null;
		int remainder = 0;
		ListNode lastNode = null;

		do {
			int v1 = 0;
			int v2 = 0;

			if (l1 != null) {
				v1 = l1.val;
				l1 = l1.next;
			}

			if (l2 != null) {
				v2 = l2.val;
				l2 = l2.next;
			}

			int sum = v1 + v2 + remainder;
			remainder = sum / 10;
			ListNode node = new ListNode(sum % 10);
			if (sumList == null) {
				sumList = node;
			} else {
				lastNode.next = node;
			}
			lastNode = node;
		} while (l1 != null || l2 != null);

		if (remainder != 0) {
			ListNode node = new ListNode(remainder);
			lastNode.next = node;
		}

		return sumList;
	}

	public static void main(String args[]) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		AddTwoNumbers addNumbers = new AddTwoNumbers();
		addNumbers.addTwoNumbers(l1, l2);
	}
}
