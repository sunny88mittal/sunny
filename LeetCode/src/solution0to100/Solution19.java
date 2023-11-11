package solution0to100;

public class Solution19 {
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

		public ListNode removeNthFromEnd(ListNode head, int n) {
			int count = 0;
			ListNode temp = head;
			while (temp != null) {
				temp = temp.next;
				++count;
			}

			if (count == n) {
				head = head.next;
				return head;
			}

			temp = head;
			for (int i = 0; i < count - n - 1; i++) {
				temp = temp.next;
			}

			temp.next = temp.next.next;
			return head;
		}
	}
}
