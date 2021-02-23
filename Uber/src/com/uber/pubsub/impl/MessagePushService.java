package com.uber.pubsub.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.uber.pubsub.entity.Subscriber;
import com.uber.pubsub.interfaces.IMessagePushService;

public class MessagePushService implements IMessagePushService {

	private ExecutorService executorservice = Executors.newFixedThreadPool(20);

	@Override
	public void pushMessage(Subscriber subscriber, String message) {
		PushMessage pm = new PushMessage(subscriber, message);
		executorservice.submit(pm);
	}

	@Override
	public void pushMessage(Subscriber subscriber, List<String> messages) {
		for (String message : messages) {
			pushMessage(subscriber, message);
		}
	}

	private static class PushMessage implements Runnable {

		private Subscriber subscriber;

		private String message;

		public PushMessage(Subscriber subscriber, String message) {
			super();
			this.subscriber = subscriber;
			this.message = message;
		}

		@Override
		public void run() {
			subscriber.reciveMessage(message);
		}
	}
}
