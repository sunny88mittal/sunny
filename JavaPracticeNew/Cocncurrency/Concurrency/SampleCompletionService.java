package Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class SampleCompletionService {

	 public static void main (String args[]) throws InterruptedException, ExecutionException {
		 Callable<Integer> callable = new Callable<Integer>() {
			    @Override
				public Integer call() throws Exception {
					Thread.sleep(2000);
					return 5 * 2;
				}
		  };
		  
		  Executor executor = Executors.newCachedThreadPool();
		  CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
		  
		  for (int i=0; i<10; i++) {
			  completionService.submit(callable);
		  }
		  
		  for (int i=0; i<10; i++) {
			  System.out.println(completionService.take().get());
		  }
	 }
}
