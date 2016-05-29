package Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SampleBarrier {

	public static void main(String args[]) throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(10, new CompletionTask());

		for (int i = 0; i < 10; i++) {
			new Thread(new Worker(barrier)).start();
		}

		Thread.sleep(10000); // give time for the programm to complete
	}

	// Task to run upon completion of all threads
	private static class CompletionTask implements Runnable {
		@Override
		public void run() {
			System.out.println("Yes!! all threads are done");
		}
	}

	// Threads which will perform some task
	private static class Worker implements Runnable {
		private CyclicBarrier barrier;

		public Worker(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			System.out.println(this.toString());
			try {
				barrier.await();
			} catch (InterruptedException e) {
				// do nothing
			} catch (BrokenBarrierException e) {
				// do nothing
			}
		}
	}
}
