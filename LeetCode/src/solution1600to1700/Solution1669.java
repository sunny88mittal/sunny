package solution1600to1700;

public class Solution1669 {

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

		public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
			ListNode nodeBeforeA = list1;
			for (int i = 1; i < a; i++) {
				nodeBeforeA = nodeBeforeA.next;
			}

			ListNode nodeB = list1;
			for (int i = 0; i < b; i++) {
				nodeB = nodeB.next;
			}

			ListNode list2LastNode = list2;
			while (list2LastNode.next != null) {
				list2LastNode = list2LastNode.next;
			}

			nodeBeforeA.next = list2;
			list2LastNode.next = nodeB.next;

			return list1;
		}
	}
}
