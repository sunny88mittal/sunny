package zookeeper.clustermanager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;

import zookeeper.base.Base;
import zookeeper.base.Constants;

public class MembershipObserver extends Base {

	public MembershipObserver(String connectionString) {
		super(connectionString);
	}

	public void observeMembershipChanges() throws InterruptedException, KeeperException {
		final Semaphore semaphore = new Semaphore(1);
		semaphore.acquire();
		while (true) {
			List<String> children = connection.getChildren(Constants.MEMEBER_NODE, new Watcher() {
				public void process(WatchedEvent event) {
					if (event.getType() == EventType.NodeChildrenChanged) {
						semaphore.release();
					}
				}
			});
			System.out.println(children);
			System.out.println("--------------");
			semaphore.acquire();
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException, KeeperException {
		MembershipObserver nodeManager = new MembershipObserver(Constants.CONNECTION_STRING);
		nodeManager.connect();
		nodeManager.createNodeIfNotExists(Constants.MEMEBER_NODE, 
				"Members nodes root".getBytes(),
				ZooDefs.Ids.OPEN_ACL_UNSAFE, 
				CreateMode.PERSISTENT);
		nodeManager.observeMembershipChanges();
	}
}
