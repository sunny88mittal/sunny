package com.junglee.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.junglee.request.RequestEvent;
import com.junglee.service.EventProcessingService;

public class EventProcessingServiceImpl implements EventProcessingService{

	private ExecutorService service = Executors.newFixedThreadPool(50);

	@Override
	public void processRequestEvent(RequestEvent reqEvent) {
		// Process event using the thread pool
	}
}
