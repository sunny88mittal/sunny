package com.uber.pubsub.impl;

import java.util.HashMap;
import java.util.Map;

import com.uber.pubsub.entity.Subscriber;
import com.uber.pubsub.interfaces.IMessagePushService;
import com.uber.pubsub.interfaces.IRouter;

public class Router implements IRouter {

	private Map<String, Channel> channelMap = new HashMap<String, Channel>();

	private IMessagePushService messagingService;

	public Router(IMessagePushService messagingService) {
		super();
		this.messagingService = messagingService;
	}

	@Override
	public void routeMessage(String channelName, String message) {
		if (channelMap.get(channelName) == null) {
			Channel channel = new Channel(channelName, messagingService);
			channelMap.put(channelName, channel);
		}

		Channel channel = channelMap.get(channelName);
		channel.acceptMessage(message);
	}

	@Override
	public void subscribeToChannel(String channelName, Subscriber subscriber) {
		Channel channel = channelMap.get(channelName);
		if (channel != null) {
			channel.addSubscriber(subscriber);
		}
	}
}
