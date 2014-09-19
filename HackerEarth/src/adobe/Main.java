package adobe;

class MyThread extends Thread {

	public void run() {

		try {

			Thread.sleep(1000L);

		}

		catch (Exception e) {

			// Do Nothing

		}

		System.out.println("Thread output");

	}

}

public class Main {

	public static void main(String[] args) {

		MyThread t = new MyThread();

		t.run();

		System.out.println("Main output");

	}

}