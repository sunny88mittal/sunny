package com.sunny.linkedlist;

public class ReverseLinkedList {

	public static void main(String args[]) {
		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6 };
		Node head = LinkedListUtil.createLinkedList(numbers);
		LinkedListUtil.printLinkedList(reverseLinkedList(head));
	}

	private static Node reverseLinkedList(Node head) {
		Node current = head;
		Node prev = null;
		Node next = null;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}

		return prev;
	}
}
