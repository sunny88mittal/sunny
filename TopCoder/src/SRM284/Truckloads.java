package SRM284;

public class Truckloads {
	
	public int numTrucks(int numCrates, int loadSize) {
	   int totalTrucks = 0;
	   if (numCrates <= loadSize) {
		   return 1;
	   }
		
	   int l = numCrates/2 ;
	   int r = numCrates - l;
	   
	   totalTrucks = numTrucks(l, loadSize) + numTrucks(r, loadSize);
	   
	   return totalTrucks;
	}
}
