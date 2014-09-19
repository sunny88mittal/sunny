package Concurrency;

import java.util.Random;

/**
 * Class which helps in producer consumer coordination
 * 
 * @author sumittal
 * 
 */
class Drop {
	// Message sent from producer
	// to consumer.
	private String message;

	private boolean isEmpty = true;

	public synchronized String take() {
		//Wait while there are no messages
		while (isEmpty) {
			try {
				//Releases the lock on the drop object and thread goes into idle mode
				//till it gets notified
				wait();
			} catch (InterruptedException e) {
				// Ignore exception
			}
		}
		isEmpty = true;
		//notify the producers
		notifyAll();
		return message;
	}

	public synchronized void put(String message) {
		//Wait while the old message has not been consumed
		while (!isEmpty) {
			try {
				//Releases the lock on the drop object and thread goes into idle mode
				//till it gets notified
				wait();
			} catch (InterruptedException e) {
				// Ignore exception
			}
		}
		isEmpty = false;
		//notify the consumers
		this.message = message;
		notifyAll();
	}
}

/**
 * Producer class which producer the messages
 * 
 * @author sumittal
 * 
 */
class Producer implements Runnable {
	private Drop drop;

	public Producer(Drop drop) {
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
class Consumer implements Runnable {
	private Drop drop;

	public Consumer(Drop drop) {
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

public class ProducerConsumerUsingGuardedBlocks {
	public static void main(String[] args) {
		Drop drop = new Drop();
		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
	}
}
