package com.sunny.queue;

import java.util.LinkedList;
import java.util.List;

public class CircularTourThatVisitsAllPetrolPumps {
	
	private static class PumpEntry {
		public int petrol;
		
		public int distance;
		
		public PumpEntry(int petrol, int distance) {
			this.petrol = petrol;
			this.distance = distance;
		}
		
		public String toString() {
		   return "{" + petrol + "," + distance + "}";	
		}
	}
	
	public static void main (String args[]) {
		List<PumpEntry> pumpEntries = new LinkedList<PumpEntry>();
		pumpEntries.add(new PumpEntry(4, 6));
		pumpEntries.add(new PumpEntry(6, 5));
		pumpEntries.add(new PumpEntry(7, 3));
		pumpEntries.add(new PumpEntry(4, 5));
		
		int count = 0;
		PumpEntry startEntry = null;
		int totalPetrol = 0;
		while (count != pumpEntries.size()) {
			startEntry = pumpEntries.remove(0);
			totalPetrol = startEntry.petrol + totalPetrol;
			if (totalPetrol > startEntry.distance) {
				totalPetrol = totalPetrol - startEntry.distance;
				count++;
			} else {
				count = 0;
				totalPetrol = 0;
			}
			pumpEntries.add(startEntry);
		}
		
		System.out.println("Start point is:" + pumpEntries.get(0));
	}
}
