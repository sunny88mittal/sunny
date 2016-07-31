package com.junglee.service;

import com.junglee.request.RequestEvent;

public interface EventProcessingService {

	public void processRequestEvent(RequestEvent reqEvent);
}
