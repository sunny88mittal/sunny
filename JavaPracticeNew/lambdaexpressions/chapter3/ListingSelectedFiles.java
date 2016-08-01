package chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ListingSelectedFiles {

	// List selected files
	public static void main(String args[]) throws IOException {
		String path1 = "/home/priyanka/Documents/computers/Dbms";
		Files.newDirectoryStream(Paths.get(path1), path -> path.toString().endsWith(".pdf"))
				.forEach(System.out::println);

	}
}
