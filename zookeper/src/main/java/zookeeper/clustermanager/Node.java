package zookeeper.clustermanager;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import zookeeper.base.Base;

public class Node extends Base implements Runnable{

	private final String nodePath;

	public Node(String nodePath, String connectionString) {
		super(connectionString);
		this.nodePath = nodePath;
	}

	public void updateDataContinuously() throws InterruptedException, KeeperException {
		for (int i = 0; i < 10; i++) {
			Thread.sleep(3000);
			byte[] data = (System.currentTimeMillis() + "").getBytes();
			updateNodeData(nodePath, data);
		}
	}

	public void run() {
		try {
			connect();
			createNodeIfNotExists(nodePath, 
					(System.currentTimeMillis() + "").getBytes(), 
					ZooDefs.Ids.OPEN_ACL_UNSAFE, 
					CreateMode.EPHEMERAL);
			updateDataContinuously();
			close();
		} catch (Exception e) {
			//Do nothing
		}
		System.out.println("Going away:" + nodePath);
	}
}
