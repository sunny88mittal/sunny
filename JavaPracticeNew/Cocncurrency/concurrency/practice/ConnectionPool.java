package concurrency.practice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {

	private BlockingQueue<String> activeConnections = new LinkedBlockingQueue<String>();

	private BlockingQueue<String> idleConnections = new LinkedBlockingQueue<String>();

	private int size;

	private AtomicInteger activeCount = new AtomicInteger(0);

	private int idealConenctionTest = 2000;

	private Thread cleanupThread = new Thread(new TestIdleConnections());

	public ConnectionPool(int size) {
		this.size = size;
		cleanupThread.start();
	}

	public String borrowConnection() {
		String conn = idleConnections.poll();
		if (conn == null) {
			if (activeCount.get() < size) {
				if (activeCount.incrementAndGet() <= size) {
					conn = "newConenction";
					activeConnections.offer(conn);
				} else {
					activeCount.decrementAndGet();
				}
			}

			if (conn == null) {
				//throw Exception
			}
			
			synchronized (conn) {
				// Do if something is to be done
			}
		}
		return conn;
	}

	public void returnConnection(String connection) {
		if (connection != null) {
			synchronized (connection) {
				activeConnections.remove(connection);
				idleConnections.offer(connection);
			}
		}
	}

	private class TestIdleConnections implements Runnable {
		public void run() {
             //Test connections here by taking lock on each connection
		}
	}
}