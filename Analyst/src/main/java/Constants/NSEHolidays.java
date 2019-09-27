package Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class NSEHolidays {

	private static Set<String> holidays = new HashSet<String>();

	static {
		holidays.add("02-10-2019");
		holidays.add("08-10-2019");
		holidays.add("28-10-2019");
		holidays.add("12-11-2019");
		holidays.add("25-12-2019");
	}

	public static boolean isHoliday(LocalDateTime date) {
		String pattern = "dd-MM-yyyy";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		String formattedDate = date.format(dateFormatter);
		return holidays.contains(formattedDate);
	}
}
