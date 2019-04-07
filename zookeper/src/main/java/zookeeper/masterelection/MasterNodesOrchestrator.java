package zookeeper.masterelection;

import java.util.ArrayList;
import java.util.List;

import zookeeper.base.Constants;

public class MasterNodesOrchestrator {

	public static void main(String args[]) throws InterruptedException {
		int count = 3;
		List<Thread> masterNodes = getNodeThreads(count);
		for (int i = 0; i < count; i++) {
			Thread th = masterNodes.get(i);
			th.start();			
		}
		
		for (int i = 0; i < count; i++) {
			Thread th = masterNodes.get(i);
			th.join();
		}
	}

	private static List<Thread> getNodeThreads(int count) {
		List<Thread> nodeThreads = new ArrayList<Thread>();
		for (int i = 0; i < count; i++) {
			MasterNode masterNode = new MasterNode("Master" + i, Constants.CONNECTION_STRING);
			Thread nodeThread = new Thread(masterNode);
			nodeThreads.add(nodeThread);
		}
		return nodeThreads;
	}
}
