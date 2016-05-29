package SRM145;

public class Bonuses {
	
	public int[] getDivision(int[] points) {
		int length = points.length;
		int bonusPer[] = new int[length];
		int totalPoints = 0;
		for (int i=0; i<length; i++) {
		    totalPoints += points[i];   	
		}
		
		int bonusLeft = 100;
		for (int i=0; i<length; i++) {
			bonusPer[i] = points[i] * 100 / totalPoints;
		    bonusLeft = bonusLeft - bonusPer[i];
		}
		
		while (bonusLeft > 0) {
			int maxI = 0;		
			for (int i=0; i<length; i++){
				if (points[i] > points[maxI]){
					maxI = i;
				}
			}
			
			++bonusPer[maxI];
			points[maxI] = 0;
			--bonusLeft;
		}
		return bonusPer;
	}
}
