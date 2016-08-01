package NIO;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

public class FileChangeWatch {

	public static void main(String args[]) throws IOException, InterruptedException {
		String path1 = "/home/priyanka/Documents/computers/Dbms";

		final Path path = Paths.get(path1);
		final WatchService watchService = path.getFileSystem().newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		System.out.println("Report any file changed within next 1 minutes...");

		final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
		if (watchKey != null) {
			watchKey.pollEvents().stream().forEach(event -> System.out.println(event.context()));
		}
	}
}
