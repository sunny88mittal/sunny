package com.sunny.test.indicators;

import org.junit.Test;

import com.sunny.indicators.MovingAverage;
import com.sunny.indicators.MovingAverage.MovingAverageType;

public class MovingAverageTest {

	private MovingAverage movingAverage;
	
	private float[] numbers = new float[]{2.0f,3.0f,4.0f,5.0f,5.0f};
	
	@Test
	public void testSimpleMovingAverage() {
		movingAverage = new MovingAverage(MovingAverageType.SIMPLE);
		float result = movingAverage.getValue(numbers);
		assert (result == 3.8f);
	}

}
