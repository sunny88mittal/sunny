package com.zookeeper.masterelection;

import java.io.IOException;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class MasterElection implements Watcher {

	private static final String ZOOKEEPER_CONNECTION = "127.0.0.1:2181";

	private static final String MASTER_NODE_NAME = "/master";

	private boolean isMaster = false;

	private String serverId;

	private ZooKeeper zk;

	public void startZk() throws IOException {
		zk = new ZooKeeper(ZOOKEEPER_CONNECTION, 10000, this);
		Random random = new Random();
		serverId = random.nextLong() + "";
	}

	public void goForElection() throws InterruptedException {
		while (true) {
			try {
				zk.create(MASTER_NODE_NAME, serverId.getBytes(),
						ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
				isMaster = true;
			} catch (NodeExistsException e) {
				System.out.println("Node already exists");
				e.printStackTrace();
				isMaster = false;
			} catch (KeeperException e) {
				System.out.println("Some Other Exception");
			}
			if (isMaster())
				break;
			Thread.sleep(6000); // Wait for few seconds
		}
	}

	public boolean isMaster() throws InterruptedException {
		while (true) {
			Stat stat = new Stat();
			try {
				byte data[] = zk.getData(MASTER_NODE_NAME, false, stat);
				isMaster = serverId.equals(new String(data));
			} catch (NodeExistsException e) {
				isMaster = false;
				System.out.println("Node Already Exists");
			} catch (KeeperException e) {
				System.out.println("Some Other Exception");
			}
			return isMaster;
		}
	}

	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub

	}

	public void stopZK() throws InterruptedException {
		zk.close();
	}

	public static void main(String args[]) throws Exception {
		MasterElection m = new MasterElection();
		m.startZk();
		m.goForElection();
		if (m.isMaster) {
			System.out.println("I'm the leader");
			// wait for a bit
			Thread.sleep(10000);
		} else {
			System.out.println("Someone else is the leader");
		}
		m.stopZK();
	}
}