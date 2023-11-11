package solution0to100;

public class Solution24 {
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

		public ListNode swapPairs(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}

			ListNode n1 = head;
			ListNode n2 = n1.next;
			ListNode n3 = n2.next;

			head = n2;
			head.next = n1;
			n1.next = swapPairs(n3);
			return head;
		}
	}
}
