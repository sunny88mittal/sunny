package Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class NSEHolidays {

	private static Set<String> holidays = new HashSet<String>();

	static {
		holidays.add("21-02-2020");
		holidays.add("10-03-2020");
		holidays.add("02-04-2020");
		holidays.add("06-04-2020");
		holidays.add("10-04-2020");
		holidays.add("14-04-2020");
		holidays.add("01-05-2020");
		holidays.add("25-05-2020");
		holidays.add("02-10-2020");
		holidays.add("16-11-2020");
		holidays.add("30-11-2020");
		holidays.add("25-12-2020");
	}

	public static boolean isHoliday(LocalDateTime date) {
		String pattern = "dd-MM-yyyy";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		String formattedDate = date.format(dateFormatter);
		return holidays.contains(formattedDate);
	}
}
