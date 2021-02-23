package com.uber.pubsub.entity;

public class SubscriberPosition {

	private Subscriber subscriber;

	private int position;

	public SubscriberPosition(Subscriber subscriber, int position) {
		super();
		this.subscriber = subscriber;
		this.position = position;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
