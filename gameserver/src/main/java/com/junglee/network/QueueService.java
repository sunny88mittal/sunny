package com.junglee.network;

import com.junglee.request.RequestEvent;

public interface QueueService {

	public void registerToQueue();
	
	public void sendEvent(RequestEvent reqEvent);
	
	public void recieveEvent(RequestEvent reqEvent);
}
