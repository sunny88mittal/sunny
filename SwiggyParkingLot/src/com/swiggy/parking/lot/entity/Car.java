package com.swiggy.parking.lot.entity;

import com.swiggy.parking.lot.exceptions.InvalidArgumentException;

public class Car extends Vehicle {

	public Car(String registrationNo, String color) throws InvalidArgumentException {
		super(registrationNo, color);
	}
}
