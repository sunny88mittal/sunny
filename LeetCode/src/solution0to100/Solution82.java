package solution0to100;

public class Solution82 {
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

		public ListNode deleteDuplicates(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}

			if (head.val != head.next.val) {
				head.next = deleteDuplicates(head.next);
			} else {
				int currentVal = head.val;
				while (head != null && head.val == currentVal) {
					head = head.next;
				}
				return deleteDuplicates(head);
			}

			return head;
		}
	}
}
