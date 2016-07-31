package com.junglee.distributed.lock.impl;

import com.junglee.distributed.lock.DistributedLockManager;
import com.junglee.distributed.lock.LockEvent;

public class DistributedLockManagerImpl implements DistributedLockManager {

	public boolean getLock(String serverId, int tableId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean releaseLock(String serverId, int tableId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onEvent(LockEvent lockEvent) {
		// TODO Auto-generated method stub

	}
}
