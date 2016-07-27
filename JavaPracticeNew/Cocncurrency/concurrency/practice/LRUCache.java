package concurrency.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K, V> {

	private final Map<K, Node> entriesMap = new HashMap<K, Node>();

	private final DoublyLinkedList linkedList = new DoublyLinkedList();

	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private final Lock r = readWriteLock.readLock();

	private final Lock w = readWriteLock.writeLock();

	private int size;

	public LRUCache(int size) {
		this.size = size;
	}

	public V get(K key) {
		r.lock();
		try {
			Node returnValue = entriesMap.get(key);
			if (returnValue != null) {
				linkedList.moveToFront(returnValue);
				return returnValue.value;
			}
			return null;
		} finally {
			r.unlock();
		}
	}

	public void put(K key, V value) {
		w.lock();
		try {
			if (entriesMap.size() >= size) {
				Node removedNode = linkedList.removeLastNode();
				entriesMap.remove(removedNode.key);
			}
			Node newNode = new Node(key, value);
			entriesMap.put(key, newNode);
			linkedList.addNode(newNode);
		} finally {
			w.unlock();
		}
	}

	private class Node {
		K key;
		V value;
		Node next;
		Node before;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private class DoublyLinkedList {
		private Node head = null;
		private Node tail = null;

		private void addNode(Node node) {
			if (head == null && tail == null) {
				head = tail = node;
			} else {
				head.before = node;
				node.next = head;
				head = node;
			}
		}

		private Node removeLastNode() {
			Node tempNode = tail;
			tail = tempNode.before;
			if (tail != null) {
				tail.next = null;
			} else {
				tail = null;
				head = null;
			}
			return tempNode;
		}

		private void moveToFront(Node node) {
			if (head == node) { // Nothing to do in this case
				return;
			}

			// Fix previous and before nodes
			Node tbefore = node.before;
			Node tnext = node.next;
			tbefore.next = tnext;
			if (tnext != null) {
				tnext.before = tbefore;
			}

			// Fix tail
			if (tail == node) {
				tail = tbefore;
			}

			// Fix head
			head.before = node;
			node.next = head;
			head = node;
		}
	}
	
	public static void main (String args[]) {
		LRUCache<String, Integer> cache = new LRUCache<>(3);
		
		cache.put("a", 1);
		cache.put("b", 2);
		cache.put("c", 3);
		
		System.out.println(cache.get("a"));
		System.out.println(cache.get("b"));
		System.out.println(cache.get("c"));
		
		cache.put("d", 4);
		cache.put("e", 5);
		
		System.out.println(cache.get("a"));
		System.out.println(cache.get("b"));
		System.out.println(cache.get("c"));
		System.out.println(cache.get("d"));
		System.out.println(cache.get("e"));
	}
}