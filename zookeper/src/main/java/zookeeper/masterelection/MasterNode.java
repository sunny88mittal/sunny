package zookeeper.masterelection;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import zookeeper.base.Base;
import zookeeper.base.Constants;

public class MasterNode extends Base implements Runnable {

	private final String nodeName;

	public MasterNode(String nodeName, String connectionString) {
		super(connectionString);
		this.nodeName = nodeName;
	}

	public void run() {
		try {
			for (int i = 0; i < 15; i++) {
				// Connect
				connect();

				createNodeIfNotExists(Constants.MASTER_NODE, nodeName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
						CreateMode.PERSISTENT);

				// Create your seq node
				String nodePath = connection.create(Constants.MASTER_NODE + "/" + "seq_", nodeName.getBytes(),
						ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			
				// Get children
				List<String> children = connection.getChildren(Constants.MASTER_NODE, false);
				Collections.sort(children);

				// Become master if smallest or listen to one node lower
				if (children.get(0).equals(nodePath.replaceAll(Constants.MASTER_NODE + "/", ""))) {
					System.out.println("Master is now: " + nodeName);
				} else {
					int indexToWatch = children.indexOf(nodePath.replaceAll(Constants.MASTER_NODE + "/", "")) - 1;
					String nodeToWatch = Constants.MASTER_NODE + "/" + children.get(indexToWatch);
					final Semaphore sempaphore = new Semaphore(1);
					Stat stat = connection.exists(nodeToWatch, new Watcher() {
						public void process(WatchedEvent event) {
							if (event.getType() == EventType.NodeDeleted) {
								sempaphore.release();
							}
						}
					});
					//If watched node does not exist become master
					if (stat == null) {
						System.out.println("Master is now: " + nodeName);
					} else {
						//Watched node gone become master
						sempaphore.acquire();
						System.out.println("Master is now: " + nodeName);
					}
				}
				Random random = new Random(5);
				Thread.sleep(1000 * random.nextInt(5));
				close();
			}
		} catch (Exception e) {
			System.out.println(nodeName);
			System.out.println(e);
		}
	}
}
