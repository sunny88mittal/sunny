package chapter1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class EchoServerNIO2 {

	public void serve(int port) throws IOException {
		// Create an asynchronous server socket channel
		final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel
				.open();
		InetSocketAddress socketAddress = new InetSocketAddress(port);
		serverSocketChannel.bind(socketAddress);

		final CountDownLatch latch = new CountDownLatch(1);

		// Add a completion handler which will be called on accepting a
		// connection
		serverSocketChannel.accept(null,
				new CompletionHandler<AsynchronousSocketChannel, Object>() {

					@Override
					public void completed(AsynchronousSocketChannel result,
							Object attachment) {
						// Re register the same completion handler to the server
						// socket channel
						serverSocketChannel.accept(null, this);
						ByteBuffer buffer = ByteBuffer.allocate(100);
						result.read(buffer, buffer, new EchoCompletionHandler(result));
					}

					@Override
					public void failed(Throwable exc, Object attachment) {
						try {
							serverSocketChannel.close();
							latch.countDown();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});

	}

	private final class EchoCompletionHandler implements
			CompletionHandler<Integer, ByteBuffer> {

		private final AsynchronousSocketChannel channel;

		public EchoCompletionHandler(AsynchronousSocketChannel channel) {
			this.channel = channel;
		}

		@Override
		public void completed(Integer result, ByteBuffer buffer) {
			buffer.flip();
			channel.write(buffer, buffer,
					new CompletionHandler<Integer, ByteBuffer>() {
						@Override
						public void completed(Integer result, ByteBuffer buffer) {
							if (buffer.hasRemaining()) {
								channel.write(buffer, buffer, this);
							} else {
								buffer.compact();
								channel.read(buffer, buffer,
										EchoCompletionHandler.this);
							}

						}

						@Override
						public void failed(Throwable exc, ByteBuffer buffer) {
							try {
								channel.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
		}

		@Override
		public void failed(Throwable exc, ByteBuffer buffer) {
			try {
				channel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
