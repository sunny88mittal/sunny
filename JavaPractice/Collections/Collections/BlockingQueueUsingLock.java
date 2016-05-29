package Collections;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueUsingLock {

	private int[] elements;

	private int currentSize = 0;

	Lock lockObj = new ReentrantLock();

	Condition queueFullCond = lockObj.newCondition();

	Condition queueEmptyCond = lockObj.newCondition();

	public BlockingQueueUsingLock(int size) throws Exception {
		if (size <= 0) {
			throw new Exception("Size cannot be less than 0");
		}
		elements = new int[size];
	}

	public void add(int element) {
		lockObj.lock();
		try {
			while (currentSize == elements.length) {
				queueFullCond.awaitUninterruptibly(); // Releases the lockObj
			}
			elements[currentSize] = element;
			currentSize++;
			queueEmptyCond.signalAll();
		} finally {
			lockObj.unlock();
		}
	}

	public int remove() {
		lockObj.lock();
		try {
			while (currentSize == 0) {
				queueEmptyCond.awaitUninterruptibly(); // Releases the lockObj
			}
			int toReturn = elements[0];
			currentSize--;
			for (int i = 0; i < currentSize; i++) {
				elements[i] = elements[i + 1];
			}
			queueFullCond.signalAll();
			return toReturn;
		} finally {
			lockObj.unlock();
		}
	}

	public static void main(String args[]) throws Exception {
		BlockingQueueUsingLock queue = new BlockingQueueUsingLock(5);
        Thread addThead = new Thread(new AddThread(queue, "AddThread"));
        Thread removeThread = new Thread(new RemoveThread(queue, "Removethread"));
        Thread addThead1 = new Thread(new AddThread(queue, "AddThread1"));
        Thread removeThread1 = new Thread(new RemoveThread(queue, "Removethread1"));
        
        addThead.start();
        addThead1.start();
        removeThread.start();
        removeThread1.start();
        
        addThead.join();
	}

	private static class AddThread implements Runnable {

		private BlockingQueueUsingLock queue;
		
		String name;

		public AddThread(BlockingQueueUsingLock queue, String name) {
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

		private BlockingQueueUsingLock queue;

		String name;
		
		public RemoveThread(BlockingQueueUsingLock queue, String name) {
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
                System.out.println(name + " remvoing from queue:" + queue.remove());
			}
		}
	}
}
