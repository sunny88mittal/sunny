package com.cleartax.impl;

import java.util.List;

import com.cleartax.base.IAlgorithm;
import com.cleartax.base.Token;
import com.cleartax.base.Token.Status;

public class GlobalFirstServeImpl implements IAlgorithm {

	@Override
	public Token getNextToken(final List<Token> tokensList) {
		Token toReturn = null;
		for (int i = 0; i < tokensList.size(); i++) {
			Token ttoken = tokensList.get(i);
			if (ttoken.getStatus() == Status.WAITING) {
				if (toReturn == null) {
					toReturn = ttoken;
				} else if (ttoken.getNumber() < toReturn.getNumber()) {
					toReturn = ttoken;
				}
			}
		}
		return toReturn;
	}
}
