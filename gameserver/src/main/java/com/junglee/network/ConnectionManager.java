package com.junglee.network;

public interface ConnectionManager {

	public void clientConnected(ClientConnection cleintConnection);
	
	public void clientDisconnected(ClientConnection cleintConnection);
}
