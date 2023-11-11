package solution0to100;

public class Solution61 {
	public class ListNode {
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

		public ListNode rotateRight(ListNode head, int k) {
			if (head == null) {
				return head;
			}

			// Get list size
			int size = 0;
			ListNode temp = head;
			while (temp != null) {
				++size;
				temp = temp.next;
			}
			k = k % size;

			if (k != 0) {
				// Get last node
				ListNode lastNode = head;
				while (lastNode.next != null) {
					lastNode = lastNode.next;
				}

				// Get rotation point
				temp = head;
				for (int i = 0; i < size - k - 1; i++) {
					temp = temp.next;
				}

				// Mark new head and set next for head and last node
				ListNode newHead = temp.next;
				temp.next = null;
				lastNode.next = head;
				head = newHead;
			}

			return head;
		}
	}
}
