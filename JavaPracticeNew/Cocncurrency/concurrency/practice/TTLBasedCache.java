package concurrency.practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TTLBasedCache<K, V> {

	private final Map<K, V> entriesMap = new HashMap<K, V>();

	private final List<Node> linkedList = new LinkedList<Node>();

	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private final Lock r = readWriteLock.readLock();

	private final Lock w = readWriteLock.writeLock();

	private int size;

	private long ttl; // in ms

	private Thread cleaupThread = new Thread(new CleanupThread());

	public TTLBasedCache(int size, long ttl) {
		this.size = size;
		this.ttl = ttl;
		cleaupThread.setDaemon(true);
		cleaupThread.start();
	}

	public V get(K key) {
		r.lock();
		try {
			return entriesMap.get(key);
		} finally {
			r.unlock();
		}
	}

	public void put(K key, V value) {
		w.lock();
		try {
			if (linkedList.size() >= size) {
				K toRemove = linkedList.remove(size - 1).key;
				entriesMap.remove(toRemove);
			}
			entriesMap.put(key, value);
			linkedList.add(0, new Node(key, System.currentTimeMillis()));
		} finally {
			w.unlock();
		}
	}

	private class Node {
		K key;
		long timestamp;

		public Node(K key, long timestamp) {
			this.key = key;
			this.timestamp = timestamp;
		}
	}

	private class CleanupThread implements Runnable {
		public void run() {
			while (true) {
				w.lock();
				try {
					Iterator<Node> iterator = linkedList.iterator();
					while (iterator.hasNext()) {
						Node node = iterator.next();
						if (node.timestamp <= System.currentTimeMillis() - ttl) {
							iterator.remove();
							entriesMap.remove(node.key);
						} else {
							break;
						}
					}
				} finally {
					w.unlock();
				}

				try {
					Thread.sleep(ttl);
				} catch (InterruptedException e) {
					// Do nothing
				}
			}
		}
	}

	public static void main(String args[]) {
		TTLBasedCache<String, Integer> cache = new TTLBasedCache<>(20, 20000);

		for (int i = 0; i < 20; i++) {
			cache.put(('a' + i) + "", i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

			}
		}

		for (int i = 19; i >= 0; i--) {
			System.out.println(cache.get(('a' + i) + ""));
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {

			}
		}
	}
}