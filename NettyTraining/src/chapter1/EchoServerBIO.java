package chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerBIO {

	public void serve(int port) throws IOException {
		final ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			// Accept connection from a client
			final Socket clientSocket = serverSocket.accept();

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// Open a stream to read from client
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(
										clientSocket.getInputStream()));

						// Open a stream to write to the client
						PrintWriter writer = new PrintWriter(
								clientSocket.getOutputStream(), true);

						// Actual logic of reading and writing
						while (true) {
							writer.println(reader.readLine());
							writer.flush();
						}
					} catch (IOException e) {
						try {
							clientSocket.close();
						} catch (IOException e1) {
							// Ignore
						}
					}
				}

			}).start();
		}
	}
}