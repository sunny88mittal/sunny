package SRM145;

public class ExerciseMachine {
	
	public int getPercentages(String time) {
		int times = 0;
		String[] timeUnits = time.split(":");
		int totalSeconds = Integer.parseInt(timeUnits[0]) * 3600 +
				       Integer.parseInt(timeUnits[1]) * 60 +
				       Integer.parseInt(timeUnits[2]);
		int splitValue = 0;
		for (int i=1; i<100; i++) {
			if ((totalSeconds *i)% 100 == 0){
				splitValue = i;
				break;
			}
		}
		
		if (splitValue !=0) {
			times = 100 / splitValue - 1;
		}

		return times;
	}
}

//Think about it using GCD
