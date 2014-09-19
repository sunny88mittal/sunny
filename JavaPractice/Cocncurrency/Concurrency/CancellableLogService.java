package Concurrency;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class CancellableLogService {
    private final BlockingQueue<String> queue = null;
    private final LoggerThread loggerThread = null;
    private final PrintWriter writer = null;;
    
    private boolean isShutdown;
    private int reservations;

    public void start() { loggerThread.start(); }

    public void stop() {
        synchronized (this) { isShutdown = true; }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown)
                throw new IllegalStateException();
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (this) {
                            if (isShutdown && reservations == 0)
                                break;
                        }
                        String msg = queue.take();
                        synchronized (this) { --reservations; }
                        writer.println(msg);
                    } catch (InterruptedException e) { /*  retry  */ }
                }
            } finally {
                writer.close();
            }
        }
    }
}