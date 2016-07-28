package Concurrency;

public class StartingThreadMultipleTimes {

	public static void main(String args[]) {	
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello");
			}
		});
		
		th2.start();
		th2.start();
	}
}
