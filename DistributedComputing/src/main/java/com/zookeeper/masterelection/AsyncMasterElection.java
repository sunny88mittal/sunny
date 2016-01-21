package com.zookeeper.masterelection;

import java.io.IOException;
import java.util.Random;

import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class AsyncMasterElection implements Watcher {

	private static final String ZOOKEEPER_CONNECTION = "127.0.0.1:2181";

	private static final String MASTER_NODE_NAME = "/master";

	private boolean isMaster = false;

	private String serverId;

	private ZooKeeper zk;

	// Callbacks

	// Callback for create node
	StringCallback masterCreateCallback = new StringCallback() {

		public void processResult(int rc, String path, Object ctx, String name) {
			switch (Code.get(rc)) {
			case CONNECTIONLOSS:
				isMaster();
				return;
			case OK:
				isMaster = true;
				break;
			default:
				isMaster = false;	
			}
			System.out.println("I am the master: " + isMaster);
		}
	};

	// Callback to check for the node exists
	DataCallback masterCheckCallback = new DataCallback() {

		public void processResult(int rc, String path, Object ctx, byte[] data,
				Stat stat) {
			switch (Code.get(rc)) {
			case CONNECTIONLOSS:
				isMaster();
				return;
			case NONODE:
				goForElection();
				return;
			}
		}
	};

	public void startZk() throws IOException {
		zk = new ZooKeeper(ZOOKEEPER_CONNECTION, 10000, this);
		Random random = new Random();
		serverId = random.nextLong() + "";
	}

	public void goForElection() {
		zk.create(MASTER_NODE_NAME, serverId.getBytes(),
				ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
				masterCreateCallback, null);
	}

	public void isMaster() {
		zk.getData(MASTER_NODE_NAME, false, masterCheckCallback, null);
	}

	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub

	}

	public void stopZK() throws InterruptedException {
		zk.close();
	}

	public static void main(String args[]) throws Exception {
		AsyncMasterElection m = new AsyncMasterElection();
		m.startZk();
		m.goForElection();
		Thread.sleep(10000);
		if (m.isMaster) {
			System.out.println("I'm the leader");
		} else {
			System.out.println("Someone else is the leader");
		}
		m.stopZK();
	}
}