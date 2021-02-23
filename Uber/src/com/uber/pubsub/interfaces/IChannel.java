package com.uber.pubsub.interfaces;
import com.uber.pubsub.entity.Subscriber;

public interface IChannel {

	public void addSubscriber(Subscriber susbcriber);

	public void removeSubscriber(Subscriber susbcriber);

	public void acceptMessage(String message);
}
