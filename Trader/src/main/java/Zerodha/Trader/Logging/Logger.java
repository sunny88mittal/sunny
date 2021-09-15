package Zerodha.Trader.Logging;

import java.time.LocalDateTime;

public class Logger {

	public static void print(Class loggingClass, String log) {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.toLocalTime() + " : " + loggingClass.getName() + " : " + log);
	}
}
