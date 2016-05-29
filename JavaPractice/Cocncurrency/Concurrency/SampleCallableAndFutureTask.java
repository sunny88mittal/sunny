package Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SampleCallableAndFutureTask {

	public static void main(String args[]) throws InterruptedException,
			ExecutionException {
		// Create a callable object
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(2000);
				return 5 * 2;
			}
		};
	
		//Create a future task
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
		
		//Create a thread to run it
		Thread thread = new Thread(futureTask);
        thread.start();
        
        //Wait for the computation to complete
		System.out.println("Wating to get the result");
		System.out.println(futureTask.get());
		System.out.println("Got the result");
	}
}
