package NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelEg {

	public static void main (String args[]) throws IOException {
		/*Using a Buffer to read and write data typically follows this little 4-step process:
	      1. Write data into the Buffer
	      2. Call buffer.flip()
	      3. Read data out of the Buffer
	      4. Call buffer.clear()
        */
		
		RandomAccessFile  file = new RandomAccessFile("C:\\Users\\sumittal\\workspace\\TopCoder\\JavaPractice\\NIO\\NIO\\nioFile.txt", "rw");
		FileChannel fileChannel = file.getChannel();
		
		//Create a byte buffer
		ByteBuffer buf = ByteBuffer.allocate(48);
		
		//Read data from the channel into the buffer
		int bytesRead = fileChannel.read(buf);
		
		//Read data from the buffer
		while (bytesRead != -1) {
			 System.out.println("Read " + bytesRead);
			 
			 // To switch the buffer from writing mode into reading mode
			 buf.flip();
			 
			 while (buf.hasRemaining()) {
				 System.out.println((char)buf.get());
			 }
			 
			 //To clear the buffer, to make it ready for writing again. You can do this in two ways: By calling clear() or by calling compact(). 
			 //The clear() method clears the whole buffer. The compact() method only clears the data which you have already read
			 buf.clear();
			 bytesRead = fileChannel.read(buf);
		}
		
		file.close();
	}
}
