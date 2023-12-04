package solution0to100;

public class Solution21 {

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
	}

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode finalList = new ListNode(-1);
		ListNode cFinalList = finalList;

		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				cFinalList = addNode(cFinalList, list1.val);
				list1 = list1.next;
			} else {
				cFinalList = addNode(cFinalList, list2.val);
				list2 = list2.next;
			}
		}

		while (list1 != null) {
			cFinalList = addNode(cFinalList, list1.val);
			list1 = list1.next;
		}

		while (list2 != null) {
			cFinalList = addNode(cFinalList, list2.val);
			list2 = list2.next;
		}

		return finalList.next;
	}

	private ListNode addNode(ListNode finalList, int val) {
		ListNode node = new ListNode(val);
		finalList.next = node;
		finalList = finalList.next;
		return finalList;
	}
}
