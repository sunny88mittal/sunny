
public class MergeKSortedLists {

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

	public ListNode mergeKLists(ListNode[] lists) {
		ListNode mergedList = null;
		ListNode mergedListCurrentNode = null;
		boolean found = true;
		while (found) {
			ListNode toUseNode = null;
			int indexOfNode = -1;
			found = false;
			for (int i = 0; i < lists.length; i++) {
				if (lists[i] != null) {
					found = true;
					if (toUseNode == null) {
						toUseNode = lists[i];
						indexOfNode = i;
					}

					if (lists[i].val < toUseNode.val) {
						toUseNode = lists[i];
						indexOfNode = i;
					}
				}
			}

			if (toUseNode != null) {
				ListNode node = new ListNode(toUseNode.val);
				if (mergedList == null) {
					mergedList = node;
					mergedListCurrentNode = node;
				} else {
					mergedListCurrentNode.next = node;
					mergedListCurrentNode = node;
				}

				lists[indexOfNode] = lists[indexOfNode].next;
			}
		}
		return mergedList;
	}
}
