package com.sunny.rule.impl;

import com.sunny.operators.CompoundRuleOperator;
import com.sunny.rule.base.IRule;

public class CompoundRuleImpl implements IRule {

	private IRule rule1;

	private IRule rule2;

	private CompoundRuleOperator operator;

	public CompoundRuleImpl(IRule rule1, IRule rule2, CompoundRuleOperator operator) {
		super();
		this.rule1 = rule1;
		this.rule2 = rule2;
		this.operator = operator;
	}

	@Override
	public boolean evaluate(String date) {
		boolean result = false;

		boolean rule1Result = rule1.evaluate(date);
		boolean rule2Result = rule2.evaluate(date);

		switch (operator) {
		case AND:
			result = rule1Result && rule2Result;
			break;
		case OR:
			result = rule1Result || rule2Result;
			break;
		default:
			break;

		}
		return result;
	}
}
