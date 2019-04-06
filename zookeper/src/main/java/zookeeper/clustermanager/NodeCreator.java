package zookeeper.clustermanager;

import java.util.ArrayList;
import java.util.List;

import zookeeper.base.Constants;

public class NodeCreator {

	public static void main(String args[]) throws InterruptedException {
		int count = 20;
		List<Thread> nodeThreads = getNodeThreads(count);
		System.out.println("Creating Nodes");
		for (int i = 0; i < count; i++) {
			Thread nodeThread = nodeThreads.get(i);
			nodeThread.start();
			Thread.sleep(3000);
		}
	}

	private static List<Thread> getNodeThreads(int count) {
		List<Thread> nodeThreads = new ArrayList<Thread>();
		for (int i = 0; i < count; i++) {
			Node node = new Node(Constants.MEMEBER_NODE + "/" + "node" + i, Constants.CONNECTION_STRING);
			Thread nodeThread = new Thread(node);
			nodeThreads.add(nodeThread);
		}
		return nodeThreads;
	}
}
