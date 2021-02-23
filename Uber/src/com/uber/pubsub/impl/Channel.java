package com.uber.pubsub.impl;

import java.util.ArrayList;
import java.util.List;

import com.uber.pubsub.entity.Subscriber;
import com.uber.pubsub.entity.SubscriberPosition;
import com.uber.pubsub.interfaces.IChannel;
import com.uber.pubsub.interfaces.IMessagePushService;

public class Channel implements IChannel {

	private String name;

	private List<String> messages;

	private List<SubscriberPosition> subscribersPositions;

	private IMessagePushService messagingservice;

	public Channel(String name, IMessagePushService messagingservice) {
		super();
		this.name = name;
		this.messagingservice = messagingservice;
		this.messages = new ArrayList<String>();
		this.subscribersPositions = new ArrayList<SubscriberPosition>();
	}

	@Override
	public synchronized void addSubscriber(Subscriber susbcriber) {
		SubscriberPosition subscriberPosition = new SubscriberPosition(susbcriber, 0);
		subscribersPositions.add(subscriberPosition);
		this.messagingservice.pushMessage(susbcriber, messages);
		subscriberPosition.setPosition(messages.size());
	}

	@Override
	public synchronized void removeSubscriber(Subscriber susbcriber) {
		SubscriberPosition subscribersPosition = null;
		for (SubscriberPosition sp : subscribersPositions) {
			if (sp.getSubscriber() == susbcriber) {
				subscribersPosition = sp;
				break;
			}
		}

		subscribersPositions.remove(subscribersPosition);
	}

	@Override
	public synchronized void acceptMessage(String message) {
		messages.add(message);
		for (SubscriberPosition sp : subscribersPositions) {
			this.messagingservice.pushMessage(sp.getSubscriber(),
					messages.subList(sp.getPosition(), messages.size()));
			sp.setPosition(messages.size());
		}
	}
}
