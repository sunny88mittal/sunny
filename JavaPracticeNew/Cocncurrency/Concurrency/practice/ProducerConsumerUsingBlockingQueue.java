package Concurrency.practice;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class which helps in producer consumer coordination
 * 
 * @author sumittal
 * 
 */
class Producer2 implements Runnable {
	private final BlockingQueue<String> queue;

	Producer2(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() {
		String importantInfo[] = { "Mares eat oats", "Does eat oats",
				"Little lambs eat ivy", "A kid will eat ivy too" };
		Random random = new Random();

		for (int i = 0; i < importantInfo.length; i++) {
			try {
				queue.put(importantInfo[i]);
				Thread.sleep(random.nextInt(5000));	
			} catch (InterruptedException e) {
			    //Ignore	
			}
		}
		
		try {	
			queue.put("DONE");
		} catch (InterruptedException e) {
			//Ignore
		}
	}
}

class Consumer2 implements Runnable {
	private final BlockingQueue<String> queue;

	Consumer2(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() {
		Random random = new Random();
		while (true) {
			try {
				String message = queue.take();
				if (!message.equals("DONE")) {
					System.out.format("MESSAGE RECEIVED: %s%n", message);
					Thread.sleep(random.nextInt(500));
				} else {
					break;
				}	
			} catch (InterruptedException e) {
				//Ignore
			}
		}
	}
}

public class ProducerConsumerUsingBlockingQueue {
	public static void main(String args[]) {
		BlockingQueue<String> q = new ArrayBlockingQueue<String>(5);
		Producer2 p = new Producer2(q);
		Consumer2 c1 = new Consumer2(q);
		new Thread(p).start();
		new Thread(c1).start();
	}
}
