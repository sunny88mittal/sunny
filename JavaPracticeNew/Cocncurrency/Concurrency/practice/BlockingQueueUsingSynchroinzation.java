package Concurrency.practice;

import java.util.Random;

public class BlockingQueueUsingSynchroinzation {

	private int[] elements;

	private int currentSize = 0;

	public BlockingQueueUsingSynchroinzation(int size) throws Exception {
		if (size <= 0) {
			throw new Exception("Size cannot be less than 0");
		}
		elements = new int[size];
	}

	public synchronized void add(int element) {
		while (currentSize == elements.length) {
			try {
				wait();
			} catch (InterruptedException e) {
				// Ignore
			}
		}
		elements[currentSize] = element;
		currentSize++;
		notifyAll();
	}

	public synchronized int remove() {
		while (currentSize == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// Ignore
			}
		}
		int toReturn = elements[0];
		currentSize--;
		for (int i = 0; i < currentSize; i++) {
			elements[i] = elements[i + 1];
		}
		notifyAll();
		return toReturn;
	}

	public static void main(String args[]) throws Exception {
		BlockingQueueUsingSynchroinzation queue = new BlockingQueueUsingSynchroinzation(
				5);
		Thread addThead = new Thread(new AddThread(queue, "AddThread"));
		Thread removeThread = new Thread(
				new RemoveThread(queue, "Removethread"));
		Thread addThead1 = new Thread(new AddThread(queue, "AddThread1"));
		Thread removeThread1 = new Thread(new RemoveThread(queue,
				"Removethread1"));

		addThead.start();
		addThead1.start();
		removeThread.start();
		removeThread1.start();

		addThead.join();
	}

	private static class AddThread implements Runnable {

		private BlockingQueueUsingSynchroinzation queue;

		String name;

		public AddThread(BlockingQueueUsingSynchroinzation queue, String name) {
			this.queue = queue;
			this.name = name;
		}

		public void run() {
			while (true) {
				Random ran = new Random();
				try {
					Thread.sleep(ran.nextInt(10) * 2000);
				} catch (InterruptedException e) {
					// do nothing
				}
				int number = ran.nextInt(10) * 5;
				System.out.println(name + " adding to queue:" + number);
				queue.add(number);
			}
		}
	}

	private static class RemoveThread implements Runnable {

		private BlockingQueueUsingSynchroinzation queue;

		String name;

		public RemoveThread(BlockingQueueUsingSynchroinzation queue, String name) {
			this.queue = queue;
			this.name = name;
		}

		public void run() {
			while (true) {
				Random ran = new Random();
				try {
					Thread.sleep(ran.nextInt(5) * 120);
				} catch (InterruptedException e) {
					// do nothing
				}
				System.out.println(name + " remvoing from queue:"
						+ queue.remove());
			}
		}
	}
}
