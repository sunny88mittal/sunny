package SRM144;

public class Time {
	
	public String whatTime(int seconds) {
		String time = "";
		
		int ss = seconds % 60;
		
		int minutes =  (seconds/ 60) % 60;
		
		int hours = seconds/ 3600;
		
		time = hours + ":" + minutes + ":" + ss;
		
		return time;
	}

}
