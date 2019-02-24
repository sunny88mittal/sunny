package com.swiggy.parking.lot.entity;

import com.swiggy.parking.lot.exceptions.InvalidArgumentException;

public class Ticket {

	private int slotNo;
	
	private Vehicle vehicle;

	public Ticket (int slotNo, Vehicle vehicle) throws InvalidArgumentException {
		if (slotNo <= 0) {
			throw new InvalidArgumentException("Slot is not valid");
		}
		this.slotNo = slotNo;
		this.vehicle = vehicle;
	}

	public int getSlotNo() {
		return slotNo;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	@Override
	public String toString() {
		return "Ticket [slotNo=" + slotNo + ", vehicle=" + vehicle + "]";
	}
}