package Collections;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HashSetPractice<T> {

	private Queue<T>[] entries;

	public HashSetPractice(int size) {
		entries = (Queue<T>[]) new Queue[1];
		for (int i = 0; i < 1; i++) {
			entries[i] = new ConcurrentLinkedQueue<T>();
		}
	}

	public void add(T t) {
		int hashCode = t.hashCode();
		int bucket = hashCode % entries.length;
		Queue<T> queue = entries[bucket];
		if (!queue.contains(t)) {
			queue.add(t);
		}
	}

	public void remove(T t) {
		int hashCode = t.hashCode();
		int bucket = hashCode % entries.length;
		Queue<T> queue = entries[bucket];
		queue.remove(t);
	}

	public boolean contains(T t) {
		int hashCode = t.hashCode();
		int bucket = hashCode % entries.length;
		Queue<T> queue = entries[bucket];
		return queue.contains(t);
	}

	public static void main(String args[]) throws InterruptedException {
		HashSetPractice<String> set = new HashSetPractice<>(20);

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
}