package concurrency.practice;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

	private List<String> activeConnections = new LinkedList<String>();

	private BlockingQueue<String> idleConnections = new LinkedBlockingQueue<String>();

	private int size;

	private Lock lock = new ReentrantLock();

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
				} else {
					activeCount.decrementAndGet();
				}
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
