package com.swiggy.parking.lot.entity;

import com.swiggy.parking.lot.exceptions.InvalidArgumentException;

public abstract class Vehicle {

	private String registrationNo;

	private String color;

	public Vehicle(String registrationNo, String color) throws InvalidArgumentException {
		if (!isValidString(registrationNo)) {
			throw new InvalidArgumentException("Registration no is not valid");
		}
		if (!isValidString(color)) {
			throw new InvalidArgumentException("Color is not valid");
		}
		this.registrationNo = registrationNo;
		this.color = color;
	}
	
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Vehicle [registrationNo=" + registrationNo + ", color=" + color + "]";
	}
	
	private boolean isValidString (String arg) {
		if (arg == null || arg.trim() == "") {
			return false;
		}
		return true;
	}
}
