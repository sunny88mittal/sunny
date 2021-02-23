package com.uber.pubusb.main;

import com.uber.pubsub.entity.Subscriber;
import com.uber.pubsub.impl.MessagePushService;
import com.uber.pubsub.impl.Router;
import com.uber.pubsub.interfaces.IMessagePushService;

public class PubsubRunner {

	public static void main(String args[]) {

		IMessagePushService messagePushService = new MessagePushService();

		Router router = new Router(messagePushService);

		router.routeMessage("Channel1", "Channel1 1");
		router.routeMessage("Channel1", "Channel1 2");
		router.routeMessage("Channel2", "Channel2 1");
		router.routeMessage("Channel2", "Channel2 2");
		router.routeMessage("Channel3", "Channel3 1");

		Subscriber subscriber1 = new Subscriber("s1");
		Subscriber subscriber2 = new Subscriber("s2");
		Subscriber subscriber3 = new Subscriber("s3");
		Subscriber subscriber4 = new Subscriber("s4");

		router.subscribeToChannel("Channel1", subscriber1);
		router.subscribeToChannel("Channel1", subscriber2);
		router.subscribeToChannel("Channel1", subscriber3);
		router.subscribeToChannel("Channel1", subscriber4);

		router.subscribeToChannel("Channel2", subscriber1);
		router.subscribeToChannel("Channel2", subscriber2);
		router.subscribeToChannel("Channel2", subscriber4);

		router.subscribeToChannel("Channel3", subscriber2);
		router.subscribeToChannel("Channel3", subscriber3);
		router.subscribeToChannel("Channel3", subscriber4);

		router.routeMessage("Channel1", "Channel1 3");
		router.routeMessage("Channel1", "Channel1 4");
		router.routeMessage("Channel2", "Channel2 3");
		router.routeMessage("Channel2", "Channel2 4");
		router.routeMessage("Channel3", "Channel3 2");
	}
}
