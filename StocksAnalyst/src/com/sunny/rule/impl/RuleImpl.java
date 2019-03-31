package com.sunny.rule.impl;

import com.sunny.indicators.base.IIndicator;
import com.sunny.operators.RuleOperator;
import com.sunny.rule.base.IRule;

public class RuleImpl implements IRule {

	private RuleOperator operatorType;

	private IIndicator indicator1;

	private IIndicator indicator2;

	public RuleImpl(IIndicator indicator1, IIndicator indicator2, RuleOperator operatorType) {
		this.indicator1 = indicator1;
		this.indicator2 = indicator2;
		this.operatorType = operatorType;
	}

	public IIndicator getIndicator1() {
		return indicator1;
	}

	public void setIndicator1(IIndicator indicator1) {
		this.indicator1 = indicator1;
	}

	public IIndicator getIndicator2() {
		return indicator2;
	}

	public void setIndicator2(IIndicator indicator2) {
		this.indicator2 = indicator2;
	}

	@Override
	public boolean evaluate(String date) {
		boolean returnValue = false;
		float indicator1Value = indicator1.getValue(date);
		float indicator2Value = indicator2.getValue(date);
		
		switch (operatorType) {
		case GreaterThan:
			returnValue = indicator1Value > indicator2Value;
			break;
		case LessThan:
			returnValue = indicator1Value < indicator2Value;
			break;
		case EqualTo:
			returnValue = indicator1Value == indicator2Value;
			break;
		default:
			break;
		}
		return returnValue;
	}
}
