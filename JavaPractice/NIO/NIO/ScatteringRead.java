package NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatteringRead {

	public static void main(String args[]) throws IOException {
		/*
		 * Using a Buffer to read and write data typically follows this little
		 * 4-step process: 1. Write data into the Buffer 2. Call buffer.flip()
		 * 3. Read data out of the Buffer 4. Call buffer.clear()
		 */

		RandomAccessFile file = new RandomAccessFile(
				"C:\\Users\\sumittal\\workspace\\TopCoder\\JavaPractice\\NIO\\NIO\\nioFile.txt",
				"rw");
		FileChannel fileChannel = file.getChannel();

		// Create a byte buffer
		ByteBuffer buf1 = ByteBuffer.allocate(48);
		ByteBuffer buf2 = ByteBuffer.allocate(48);

		ByteBuffer[] bufferArray = { buf1, buf2 };

		// Read data from the channel into the buffers
		fileChannel.read(bufferArray);

		buf1.flip();
		System.out.println("Reading from buf1");
		while (buf1.hasRemaining()) {
			System.out.println((char) buf1.get());
		}

		buf2.flip();
		System.out.println("Reading from buf2");
		while (buf2.hasRemaining()) {
			System.out.println((char) buf2.get());
		}

		file.close();
	}
}
