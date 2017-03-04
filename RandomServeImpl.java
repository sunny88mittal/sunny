package com.cleartax.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.cleartax.base.IAlgorithm;
import com.cleartax.base.Token;
import com.cleartax.base.Token.Status;

public class RandomServeImpl implements IAlgorithm {

	private Random random = new Random();

	@Override
	public Token getNextToken(final List<Token> tokensList) {
		List<Token> waitingTokens = new LinkedList<Token>();
		for (Token token : tokensList) {
			if (token.getStatus() == Status.WAITING) {
				waitingTokens.add(token);
			}
		}

		if (waitingTokens.size() > 0) {
			int ran = random.nextInt(waitingTokens.size());
			return waitingTokens.get(ran);
		}

		return null;
	}
}
