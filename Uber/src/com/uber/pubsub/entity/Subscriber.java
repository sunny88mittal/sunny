package com.uber.pubsub.entity;

import com.uber.pubsub.interfaces.ISubscriber;

public class Subscriber implements ISubscriber {

	private String name;

	public Subscriber(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void reciveMessage(String message) {
		System.out.println(name + " -- " + message);
	}
}
