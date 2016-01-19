package com.zookeeper.masterelection;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Simple Program to test the connectivity with Zookeeper and receive an event
 * @author sunny
 *
 */
public class Master implements Watcher {

	private static final String ZOOKEEPER_CONNECTION = "127.0.0.1:2181";

	private ZooKeeper zk;

	public void startZk() throws IOException {
		zk = new ZooKeeper(ZOOKEEPER_CONNECTION, 10000, this);
	}

	public void process(WatchedEvent e) {
		System.out.println("Recieved an event");
		System.out.println();
		System.out.println(e);
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		System.out.println("Starting the node1 programm");
		Master node = new Master();
		node.startZk();
		Thread.sleep(20000);
	}
}
