package curator.masterelection;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;

import curator.Constants;

public class ParticipatingNode implements Runnable {

	private final String nodeName;

	private final String connectionStirng;

	private final String masterNodePath;

	public ParticipatingNode(String nodeName, String connectionStirng, String masterNodePath) {
		super();
		this.nodeName = nodeName;
		this.connectionStirng = connectionStirng;
		this.masterNodePath = masterNodePath;
	}

	public void run() {
		// Create client
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStirng, retryPolicy);
		client.start();

		// Participate in election
		for (int i = 0; i < 10; i++) {
			LeaderLatch leaderLatch = new LeaderLatch(client, masterNodePath, nodeName);
			try {
				leaderLatch.start();
				leaderLatch.await();
				if (leaderLatch.hasLeadership()) {
					System.out.println("Current leader is:" + nodeName);
				}
				Thread.sleep(1000);
				leaderLatch.close();
			} catch (Exception e) {
				System.out.println("Exception in " + nodeName + e.getMessage());
			} 
		}
		
		client.close();
	}

	public static void main(String args[]) throws InterruptedException {
		int count = 5;
		List<Thread> participatingNodesThreads = getParticipatingNodesThreads(count);

		// Start threads
		System.out.println("Starting threads");
		for (Thread th : participatingNodesThreads) {
			th.start();
		}

		// Wait for them to complete
		System.out.println("Waiting for threads");
		for (Thread th : participatingNodesThreads) {
			th.join();
		}

		System.out.println("Finished");
	}

	private static List<Thread> getParticipatingNodesThreads(int count) {
		List<Thread> participatingNodesThreads = new ArrayList<Thread>();
		for (int i = 0; i < count; i++) {
			ParticipatingNode node = new ParticipatingNode("ParticipatingNode-" + i, Constants.CONNECTION_STRING,
					Constants.MASTER_NODE);
			Thread th = new Thread(node);
			participatingNodesThreads.add(th);
		}
		return participatingNodesThreads;
	}
}