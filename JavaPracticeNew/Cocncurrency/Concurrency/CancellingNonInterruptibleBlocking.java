package Concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class CancellingNonInterruptibleBlocking {
	
	private static class ReaderThread extends Thread {
	    private final Socket socket;
	    private final InputStream in;

	    public ReaderThread(Socket socket) throws IOException {
	        this.socket = socket;
	        this.in = socket.getInputStream();
	    }

	    //Since I/o is non interruptible interrupt the task by closing the 
	    //underlying session
	    public void  interrupt()  {
	        try {
	            socket.close();
	        }
	        catch (IOException ignored) { }
	        finally {
	            super.interrupt();
	        }
	    }

	    public void run() {
	        try {
	            byte[] buf = new byte[500000];
	            while (true) {
	                int count = in.read(buf);
	                if (count < 0)
	                    break;
	                else if (count > 0) {
	                   	for (int i=0; i<count; i++) {
	                   		System.out.println(buf[i]);
	                   	}
	                }
	            }
	        } catch (IOException e) { /*  Allow thread to exit  */  }
	    }
	}
	
	public static void main (String args[]) {
		
	}
}
