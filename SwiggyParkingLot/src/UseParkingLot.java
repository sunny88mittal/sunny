import com.swiggy.parking.lot.entity.Car;
import com.swiggy.parking.lot.entity.Ticket;
import com.swiggy.parking.lot.entity.Vehicle;
import com.swiggy.parking.lot.system.abstr.ParkingLot;
import com.swiggy.parking.lot.system.impl.SimpleParkingLot;

public class UseParkingLot {

	public static void main (String args[]) throws Exception {
		//Parking Lot
		ParkingLot parkingLot = new SimpleParkingLot(10);
		
		//Vehicles
		Vehicle v1 = new Car("ABCXY1", "Red1");
		Vehicle v2 = new Car("ABCXY2", "Red2");
		Vehicle v3 = new Car("ABCXY3", "Red3");
		Vehicle v4 = new Car("ABCXY4", "Red4");
		Vehicle v5 = new Car("ABCXY5", "Red5");
		
		Ticket t1 = parkingLot.getTikcet(v1);
		System.out.println("Parked in :"  + t1.getSlotNo());
		
		Ticket t2 = parkingLot.getTikcet(v2);
		System.out.println("Parked in :"  + t2.getSlotNo());
		
		parkingLot.vacateSlot(t1);
		System.out.println("Free Parking slots:");
		for(int a :  parkingLot.getFreeSlots()) {
			System.out.println(a);
		}
		
		Ticket t3 = parkingLot.getTikcet(v3);
		System.out.println("Parked in :"  + t3.getSlotNo());
		
		Ticket t4 = parkingLot.getTikcet(v4);
		System.out.println("Parked in :"  + t4.getSlotNo());
		
		parkingLot.vacateSlot(t2);
		System.out.println("Free Parking slots:");
		for(int a :  parkingLot.getFreeSlots()) {
			System.out.println(a);
		}
		
		Ticket t5 = parkingLot.getTikcet(v5);
		System.out.println("Parked in :"  + t5.getSlotNo());
		
		System.out.println("Occupried slots info:");
		for (Ticket t : parkingLot.getAllocatedSlotsInfo()) {
			System.out.println(t);
		}
	}
}
