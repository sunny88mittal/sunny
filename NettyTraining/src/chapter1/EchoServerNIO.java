package chapter1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class EchoServerNIO {

	public void serve(int port) {
		try {
			// Create a server socket channel and bind it to an address
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			ServerSocket serverSocket = serverSocketChannel.socket();
			InetSocketAddress socketAddress = new InetSocketAddress(port);
			serverSocket.bind(socketAddress);
			serverSocketChannel.configureBlocking(false);

			// Register the server channel to the selector for accept operations
			Selector selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				// Get the channels from the selector on which something has
				// arrived
				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				for (SelectionKey selectionKey : selectedKeys) {
					//Remove the key as we have processed it
					selectedKeys.remove(selectionKey);
					
					// Accept Connections
					if (selectionKey.isAcceptable()) {
						// Get the server socket channel
						ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

						// Accept a client connection
						SocketChannel client = server.accept();
						client.configureBlocking(false);

						// Register the client channel to the selector
						client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE,
								ByteBuffer.allocate(100));
					}

					//Read clients data
					if (selectionKey.isReadable()) {
					    SocketChannel client = (SocketChannel) selectionKey.channel();
					    ByteBuffer output = (ByteBuffer) selectionKey.attachment();
					    client.read(output);
					}

					//Write back to the client
					if (selectionKey.isWritable()) {
						SocketChannel client = (SocketChannel) selectionKey.channel();
						ByteBuffer output = (ByteBuffer) selectionKey.attachment();
						output.flip();
						client.write(output);
						output.compact();
					}
				}
			}
		} catch (IOException ex) {

		}
	}
}
