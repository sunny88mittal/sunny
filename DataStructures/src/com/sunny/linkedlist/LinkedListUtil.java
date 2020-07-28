package com.sunny.linkedlist;

public class LinkedListUtil {

	public static Node createLinkedList(int[] numbers) {
		Node head = new Node(numbers[0], null);
		Node prev = head;

		for (int i = 1; i < numbers.length; i++) {
			Node current = new Node(numbers[i], null);
			prev.next = current;
			prev = current;
		}

		return head;
	}
}
