package com.uber.pubsub.interfaces;

import com.uber.pubsub.entity.Subscriber;

public interface IRouter {

	public void routeMessage(String channelName, String message);

	public void subscribeToChannel(String channelName, Subscriber subscriber);
}
