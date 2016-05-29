package Concurrency;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class which helps in producer consumer coordination
 * 
 * @author sumittal
 * 
 */
class Drop1 {
	// Message sent from producer
	// to consumer.
	private String message;

	private boolean isEmpty = true;
	
	private Lock lock = new ReentrantLock();
	
	private Condition noMessageCond = lock.newCondition();
	
	private Condition hasMessageCond = lock.newCondition();

	public String take() {
		lock.lock();
		try {
			while (isEmpty)
				hasMessageCond.awaitUninterruptibly();
			isEmpty = true;
			noMessageCond.signalAll();
			return message;	
		} finally {
			lock.unlock();
		}
	}

	public void put(String message) {
		lock.lock();
		try {
			while (!isEmpty)
				noMessageCond.awaitUninterruptibly();
			isEmpty = false;
			this.message = message;
			hasMessageCond.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * Producer class which producer the messages
 * 
 * @author sumittal
 * 
 */
class Producer1 implements Runnable {
	private Drop1 drop;

	public Producer1(Drop1 drop) {
		this.drop = drop;
	}

	public void run() {
		String importantInfo[] = { "Mares eat oats", "Does eat oats",
				"Little lambs eat ivy", "A kid will eat ivy too" };
		Random random = new Random();

		for (int i = 0; i < importantInfo.length; i++) {
			drop.put(importantInfo[i]);
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}
		drop.put("DONE");
	}
}

/**
 * Consumer class which consumes the messages
 * 
 * @author sumittal
 * 
 */
class Consumer1 implements Runnable {
	private Drop1 drop;

	public Consumer1(Drop1 drop) {
		this.drop = drop;
	}

	public void run() {
		Random random = new Random();
		for (String message = drop.take(); !message.equals("DONE"); message = drop
				.take()) {
			System.out.format("MESSAGE RECEIVED: %s%n", message);
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}
	}
}

public class ProducerConsumerUsingLock {
	public static void main(String[] args) {
		Drop1 drop = new Drop1();
		(new Thread(new Producer1(drop))).start();
		(new Thread(new Consumer1(drop))).start();
	}
}
