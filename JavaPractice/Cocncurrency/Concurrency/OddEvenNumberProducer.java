package Concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenNumberProducer {

	private static class NumberThread extends Thread {
		int startingNumber;
		AtomicInteger numSeq;
		int runAtRemainder;
		ReentrantLock lock;
		Condition waitCondition;
		Condition notifyCondition;

		public NumberThread(int startingNumber, AtomicInteger numSeq, int runAtRemainder, ReentrantLock lock,
				Condition waitCondition, Condition notifyCondition) {
			super();
			this.startingNumber = startingNumber;
			this.numSeq = numSeq;
			this.runAtRemainder = runAtRemainder;
			this.lock = lock;
			this.waitCondition = waitCondition;
			this.notifyCondition = notifyCondition;
		}

		public void run() {
			lock.lock();
			while (true) {
				if (numSeq.get() % 2 == runAtRemainder) {
					System.out.println(startingNumber);
					startingNumber += 2;
					numSeq.incrementAndGet();
					notifyCondition.signal();
				}
				waitCondition.awaitUninterruptibly();
			}
		}
	}

	public static void main(String args[]) throws InterruptedException {
		ReentrantLock lock = new ReentrantLock();
		Condition oddCondition = lock.newCondition();
		Condition evenCondition = lock.newCondition();
		AtomicInteger numSeq = new AtomicInteger(0);

		NumberThread oddThread = new NumberThread(1, numSeq, 1, lock, oddCondition, evenCondition);
		NumberThread evenThread = new NumberThread(0, numSeq, 0, lock, evenCondition, oddCondition);

		oddThread.start();
		evenThread.start();

		oddThread.join();
		evenThread.join();
	}
}
