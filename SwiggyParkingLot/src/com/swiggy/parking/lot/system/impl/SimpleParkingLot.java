package com.swiggy.parking.lot.system.impl;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import com.swiggy.parking.lot.entity.Ticket;
import com.swiggy.parking.lot.entity.Vehicle;
import com.swiggy.parking.lot.exceptions.InvalidArgumentException;
import com.swiggy.parking.lot.system.abstr.ParkingLot;

public class SimpleParkingLot implements ParkingLot {

	private Queue<Integer> emptySlots = new PriorityQueue<>();

	private Map<Integer, Ticket> occupiedSlotTickets = new ConcurrentHashMap<>();

	public SimpleParkingLot(int size) throws InvalidArgumentException {
		if (size <= 0) {
			throw new InvalidArgumentException("Parking lot size should be a valid positive no.");
		}
		for (int i = 1; i <= size; i++) {
			emptySlots.add(i);
		}
	}

	public Integer[] getFreeSlots() {
		return emptySlots.toArray(new Integer[0]);
	}

	public Ticket[] getAllocatedSlotsInfo() {
		return occupiedSlotTickets.values().toArray(new Ticket[0]);
	}

	public synchronized Ticket getTikcet(Vehicle vehicle) throws Exception {
		if (vehicle == null) {
			throw new Exception("Invalid vehicle, cannot be null");
		}
		if (emptySlots.isEmpty()) {
			throw new Exception("No parking slot available");
		}
		int slotNo = emptySlots.remove();
		Ticket t = new Ticket(slotNo, vehicle);
		occupiedSlotTickets.put(slotNo, t);
		return t;
	}

	public synchronized void vacateSlot(Ticket t) {
		int slotNo = t.getSlotNo();
		occupiedSlotTickets.remove(slotNo);
		emptySlots.add(slotNo);
	}
}