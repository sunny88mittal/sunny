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

	public static void printLinkedList(Node head) {
		while (head != null) {
			System.out.println(head.value);
			head = head.next;
		}
	}

	public static void main(String args[]) {
		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6 };
		Node linkedList = createLinkedList(numbers);
		printLinkedList(linkedList);
	}
}
