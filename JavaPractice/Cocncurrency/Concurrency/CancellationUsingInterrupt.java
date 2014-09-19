package Concurrency;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CancellationUsingInterrupt {
	
	private static class PrimeProducer extends Thread {
	    private final BlockingQueue<BigInteger> queue;

	    PrimeProducer(BlockingQueue<BigInteger> queue) {
	        this.queue = queue;
	    }

	    public void run() {
	        try {
	            BigInteger p = BigInteger.ONE;
	            while (!Thread.currentThread().isInterrupted())
	                queue.put(p = p.nextProbablePrime());
	        } catch (InterruptedException consumed) {
	        	 System.out.println("Exiting after being interrupted");
	        }
	        System.out.println(queue.size());
	    }
	    public void cancel() { interrupt(); }
	}
	
	public static void main (String args[]) throws InterruptedException {
		BlockingQueue<BigInteger> blockingQueue = new ArrayBlockingQueue<BigInteger>(2000);
		PrimeProducer primeProducer = new PrimeProducer(blockingQueue);
		
		primeProducer.start();
		primeProducer.join(200);
		primeProducer.cancel();
	}
}
