package SRM511;

public class Zoo {
	
	public long theCount(int[] answers) {
		long possibleConf = 0;
		int [] tallersCount = new int[41];
		for (int i=0; i<answers.length; i++) {
			tallersCount[answers[i]]++;
		}
		
		int doubles = 0;
		int singles = 0;
		
		for (int i=0; i<40; i++) {
			int tallersi = tallersCount[i];
			if (tallersi > 2) {
				return 0;
			}
			int tallersi1 = tallersCount[i+1];
			if (tallersi1 > tallersi) {
				return 0;
			}
			
			if (tallersi == 1) {
				singles++;
			}
			if (tallersi == 2) {
				doubles++;
			}
		}
		
		int tallers40 = tallersCount[40];
		if (tallers40 > 2) {
			return 0;
		}
		if (tallers40 == 1) {
			singles++;
		}
		if (tallers40 == 2) {
			doubles++;
		}
		
		if (doubles == 0) {
			if (singles == 0) {
				possibleConf = 0;
			} else {
				possibleConf = 2;
			}
		} else {
			if (singles == 0) {
				possibleConf = (long) (1 * Math.pow(2, doubles));	
			} else {
				possibleConf = (long) (2 * Math.pow(2, doubles));	
			}
		}
		
		return possibleConf;
	}

}
