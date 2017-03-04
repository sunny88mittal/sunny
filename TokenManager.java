package com.cleartax.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cleartax.base.IAlgorithm;
import com.cleartax.base.ProcessState;
import com.cleartax.base.Token;
import com.cleartax.base.Token.Status;

public final class TokenManager {

	private final Map<ProcessState, List<Token>> tokensMap = new HashMap<ProcessState, List<Token>>();

	private final List<Token> processedTokens = new LinkedList<Token>();

	private final IAlgorithm tokenSelectionAlgorithm;

	public TokenManager(IAlgorithm tokenSelectionAlgorithm) {
		this.tokenSelectionAlgorithm = tokenSelectionAlgorithm;
	}

	public void addTokens(List<Token> tokens) {
		for (Token token : tokens) {
			ProcessState processState = token.getProcessState();
			List<Token> tokensList = tokensMap.get(processState);
			if (tokensList == null) {
				tokensList = new LinkedList<Token>();
				tokensMap.put(processState, tokensList);
			}
			tokensList.add(token);
		}
	}

	public Token getNextToken(ProcessState processState) {
		List<Token> tokensList = tokensMap.get(processState);
		if (tokensList != null && tokensList.size() > 0) {
			return tokenSelectionAlgorithm.getNextToken(tokensList);
		}
		return null;
	}

	public void stepProcessed(Token token) {
		ProcessState currentState = token.getProcessState();
		List<Token> toRemoveFrom = tokensMap.get(currentState);
		toRemoveFrom.remove(token);

		ProcessState nextState = ProcessState.getNextState(currentState);
		if (nextState == ProcessState.PROCESS_FINISHED) {
			processedTokens.add(token);
		} else {
			token.setProcessState(nextState);
			token.setStatus(Status.WAITING);
			List<Token> toAddTo = tokensMap.get(nextState);
			if (toAddTo == null) {
				toAddTo = new LinkedList<Token>();
				tokensMap.put(nextState, toAddTo);
			}
			toAddTo.add(token);
		}
	}

	public List<Token> getProcessedTokens() {
		return processedTokens;
	}

	public void updateTokenTimes(int time) {
		for (List<Token> tokens : tokensMap.values()) {
			for (Token token : tokens) {
				token.setTimeConsumed(time);
			}
		}
	}
}