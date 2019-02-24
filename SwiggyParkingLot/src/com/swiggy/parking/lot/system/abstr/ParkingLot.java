package com.swiggy.parking.lot.system.abstr;

import com.swiggy.parking.lot.entity.Ticket;
import com.swiggy.parking.lot.entity.Vehicle;

public interface ParkingLot {

	public Integer[] getFreeSlots();
	
	public Ticket[] getAllocatedSlotsInfo();
	
	public Ticket getTikcet(Vehicle vehicle) throws Exception;
	
	public void vacateSlot(Ticket t);
}
