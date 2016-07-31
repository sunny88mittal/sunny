package com.junglee.distributed.lock;

//Implement it using Zookeeper
public interface DistributedLockManager {

	public boolean getLock(String serverId, int tableId, int userId);

	public boolean releaseLock(String serverId, int tableId, int userId);

	//An asynchronous listener to get notifications on lock release etc
	//so that other users can accquire it
	public void onEvent(LockEvent lockEvent);
}
