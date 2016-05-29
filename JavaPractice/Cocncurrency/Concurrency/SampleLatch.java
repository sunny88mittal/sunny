package Concurrency;

import java.util.concurrent.CountDownLatch;

public class SampleLatch {
	public static void main(String args[]) throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(10); 
		for (int i = 0; i < 10; ++i) {
			// create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();

		}
			
		Thread.sleep(2000); // don't let run yet
		startSignal.countDown();
		Thread.sleep(2000);
		doneSignal.await();
	}
	
	private static class Worker implements Runnable {
	    private CountDownLatch startSignal;
	    private CountDownLatch doneSignal;
	    
	    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
	    	this.startSignal =  startSignal;
	    	this.doneSignal =  doneSignal;
	    }
	    
	    @Override
		public void run() {
		    try {
				startSignal.await();
			} catch (InterruptedException e) {
				//Ignore
			}
		    doWork();
		    doneSignal.countDown();
		}
		
		void doWork() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				//do nothing
			}
			System.out.println(this.toString());
		}
	}
}