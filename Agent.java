package com.cleartax.impl;

import java.util.Random;

import com.cleartax.base.IAgent;
import com.cleartax.base.ProcessState;
import com.cleartax.base.Token;
import com.cleartax.base.Token.Status;

public class Agent implements IAgent {

	private final ProcessState agentType;

	private final int minTime;

	private final int maxTime;
	
	private Random random = new Random();
	
	private TokenManager tokenManager;
	
	private Token currentToken;
	
	private int toGetFreeAfter;

	public Agent(ProcessState agentType, int minTime, int maxTime, TokenManager tokenManager) {
		this.agentType = agentType;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.tokenManager = tokenManager;
	}

	public ProcessState getAgentType() {
		return agentType;
	}

	public int getMinTime() {
		return minTime;
	}

	public int getMaxTime() {
		return maxTime;
	}
	
	@Override
	public void takeAction() {
		if (currentToken != null) {
		   	-- toGetFreeAfter;
		   	if (toGetFreeAfter == 0) {
		   		tokenManager.stepProcessed(currentToken);
				currentToken = null;
		   	}
		}
		
		if (currentToken == null) {
			Token ttoken = tokenManager.getNextToken(agentType);
			if (ttoken != null) {
				int remainingTime = 60 - ttoken.getTimeConsumed();
				int ran = minTime + random.nextInt(maxTime - minTime + 1);
				if (ran <= remainingTime) {
					currentToken = ttoken;
					currentToken.setStatus(Status.PROCESSING);
					toGetFreeAfter = ran;
				}	
			}
		}
	}
}