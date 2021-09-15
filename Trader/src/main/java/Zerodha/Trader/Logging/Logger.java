package Zerodha.Trader.Logging;

import java.time.LocalDateTime;

public class Logger {

	public static void print(String className, String log) {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.toLocalTime() + " : " + className + " : " + log);
	}
}
