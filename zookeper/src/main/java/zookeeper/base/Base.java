package zookeeper.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

/**
 * Base class abstracting out basic zookeper stuff
 * 
 * @author sunmitta
 *
 */
public class Base {

	private final String connectionString;

	protected ZooKeeper connection;

	private static int sessionTimeout = 100;

	public Base(String connectionString) {
		super();
		this.connectionString = connectionString;
	}

	/**
	 * Create connection
	 * @throws IOException
	 * @throws InterruptedException
	 */
	protected void connect() throws IOException, InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		connection = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
			public void process(WatchedEvent event) {
				// Check if connection creation happened successfully
				if (event.getState() == KeeperState.SyncConnected) {
					latch.countDown();
				}
			}
		});
		latch.await();
	}

	/**
	 * Create node if it does not exists
	 * @param path
	 * @param data
	 * @param acl
	 * @param createMode
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	protected void createNodeIfNotExists(String path, byte[] data, ArrayList<ACL> acl, CreateMode createMode)
			throws KeeperException, InterruptedException {
		if (connection.exists(path, false) == null) {
			connection.create(path, data, acl, createMode);
			System.out.println("Node created at:" + path);
		} else {
			System.out.println(path + "Already exists");
		}
	}

	/**
	 * Update node data
	 * @param path
	 * @param data
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	protected void updateNodeData(String path, byte[] data) throws KeeperException, InterruptedException {
		connection.setData(path, data, -1);
	}
	
	protected void close() throws InterruptedException {
		connection.close();	
	}
}
