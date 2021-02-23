package com.uber.pubsub.interfaces;
import java.util.List;

import com.uber.pubsub.entity.Subscriber;

public interface IMessagePushService {

	public void pushMessage(Subscriber subscriber, String message);

	public void pushMessage(Subscriber subscriber, List<String> messages);
}
