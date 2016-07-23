package Concurrency;

import java.util.concurrent.Semaphore;

public class SampleSemaphore {

	public static void main(String args[]) throws InterruptedException {
		Semaphore sempaphore = new Semaphore(2);

		for (int i = 0; i < 10; ++i) {
			// create and start threads
			new Thread(new Worker(sempaphore, i)).start();
		}

		Thread.sleep(20000);
	}

	private static class Worker implements Runnable {
		private Semaphore semaphore;

		int name;

		public Worker(Semaphore semaphore, int name) {
			this.semaphore = semaphore;
			this.name = name;
		}

		@Override
		public void run() {
			try {
				System.out.println(name + ":going for pernmit");
				semaphore.acquire();
				System.out.println(name + ":got the pernmit");
				Thread.sleep(2000);
				semaphore.release();
				System.out.println(name + ":released the pernmit");
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

}
