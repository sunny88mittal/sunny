package chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ListingFileInADirectory {

	public static void main(String args[]) throws IOException {
		// Gets both the directories and diles
		String path = "/home/priyanka/Documents/computers/Dbms";
		Files.list(Paths.get(path)).forEach(System.out::println);

		// Only directories
		Files.list(Paths.get(path)).filter(Files::isDirectory).forEach(System.out::println);
	}
}
