package SRM623;

public class CatAndRat {
	
	public double getTime(int R, int T, int Vrat, int Vcat) {
		if (Vrat >= Vcat) { //rat faster than cat
			return -1.0;
		}
		
		double circ = 2 * Math.PI * R;
		if (T * Vrat < circ / 2) { //Rat cannot reach the center before the cat starts so it will run in the same direction when cat arrives
			return (T * Vrat * 1.0)/(Vcat - Vrat);
		}
				
		return (circ /2) / (Vcat - Vrat); //Rat will stay in the middle till the cat arrives
	}
}
