package Constants;

import java.util.ArrayList;
import java.util.List;

public class CandleStickInterval {

	public static String MINUTE_1 = "1minute";

	public static String MINUTE_2 = "2minute";

	public static String MINUTE_3 = "3minute";

	public static String MINUTE_4 = "4minute";

	public static String MINUTE_5 = "5minute";

	public static String MINUTE_10 = "10minute";

	public static String MINUTE_15 = "15minute";

	public static String MINUTE_30 = "30minute";

	public static String MINUTE_60 = "60minute";
	
	public static String HOUR_2 = "2hour";
	
	public static String HOUR_3 = "3hour";

	public static String DAY = "day";

	public static List<String> getAllIntervals() {
		List<String> allIntervals = new ArrayList<String>();
		allIntervals.add(MINUTE_3);
		allIntervals.add(MINUTE_5);
		allIntervals.add(MINUTE_10);
		allIntervals.add(MINUTE_15);
		allIntervals.add(MINUTE_30);
		allIntervals.add(MINUTE_60);
		allIntervals.add(HOUR_2);
		allIntervals.add(HOUR_3);
		allIntervals.add(DAY);
		return allIntervals;
	}
}
