package adobe;

public class ExecuteLongJob {

	private static boolean interruptTask = false;

	private static class MyTask extends Thread {
		
		public void run() {

			// Execute some steps until signaled to cancel

			while (!interruptTask) {
				try {
					System.out.println("Sleeping for 10 sec");
					Thread.sleep(10000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {

		// Start Executing the Long Running Task

		Thread task = new MyTask();

		task.start();

		// Wait for 5 seconds and then interrupt the task

		Thread.sleep(5000L);

		interruptTask = true;

		// Wait for the thread to expire

		task.join();

	}
}