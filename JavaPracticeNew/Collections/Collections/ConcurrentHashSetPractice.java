package Collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentHashSetPractice<T> {

	private Bucket[] buckets;

	public ConcurrentHashSetPractice(int size) {
		buckets = (ConcurrentHashSetPractice<T>.Bucket[]) new ConcurrentHashSetPractice<?>.Bucket[size / 10];
		for (int i = 0; i < 1; i++) {
			buckets[i] = new Bucket();
		}
	}

	public boolean add(T t) {
		int hashCode = t.hashCode();
		int ibucket = hashCode % buckets.length;
		Bucket bucket = buckets[ibucket];
		return bucket.addIfNotExists(t);
	}

	public boolean remove(T t) {
		int hashCode = t.hashCode();
		int ibucket = hashCode % buckets.length;
		Bucket bucket = buckets[ibucket];
		return bucket.remove(t);
	}

	public boolean contains(T t) {
		int hashCode = t.hashCode();
		int ibucket = hashCode % buckets.length;
		Bucket bucket = buckets[ibucket];
		return bucket.contains(t);
	}

	public static void main(String args[]) throws InterruptedException {
		ConcurrentHashSetPractice<String> set = new ConcurrentHashSetPractice<>(20);

		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				set.add("abc");
				set.add("cde");
				set.add("efg");
				set.add("ghi");
			}
		});

		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(set.contains("abc"));
				System.out.println(set.contains("cde"));
				System.out.println(set.contains("efg"));
				System.out.println(set.contains("ghi"));
			}
		});

		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				set.add("abc");
				set.add("efg");
				set.remove("abc");
				set.remove("efg");
			}
		});

		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(set.contains("abc"));
				System.out.println(set.contains("cde"));
				System.out.println(set.contains("efg"));
				System.out.println(set.contains("ghi"));
			}
		});

		th.start();
		th1.start();
		th2.start();
		th3.start();

		th.join();
		th1.join();
		th2.join();
		th3.join();
	}

	private class Bucket {
		private List<T> entries = new LinkedList<T>();

		private ReadWriteLock rwlock = new ReentrantReadWriteLock();

		private Lock rLock = rwlock.readLock();

		private Lock wLock = rwlock.writeLock();

		public boolean addIfNotExists(T t) {
			wLock.lock();
			try {
				boolean contains = false;
				for (T it : entries) {
					if (t.equals(it)) {
						contains = true;
						break;
					}
				}

				if (!contains) {
					entries.add(t);
					return true;
				}
			} finally {
				wLock.unlock();
			}
			return false;
		}

		public boolean remove(T t) {
			wLock.lock();
			try {
				Iterator<T> iterator = entries.iterator();
				while (iterator.hasNext()) {
					T current = iterator.next();
					if (current.equals(t)) {
						iterator.remove();
						return true;
					}
				}
			} finally {
				wLock.unlock();
			}
			return false;
		}

		public boolean contains(T t) {
			rLock.lock();
			try {
				for (T it : entries) {
					if (t.equals(it)) {
						return true;
					}
				}
			} finally {
				rLock.unlock();
			}
			return false;
		}
	}
}