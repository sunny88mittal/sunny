package Concurrency;

public class ObjectAndClassLevelLocking {  
    public static void main(String[] args) throws Exception {  
        Thread t1 = new Thread(new Runnable() {  
            public void run() {  
                try {  
                	ObjectAndClassLevelLocking.m1();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        });  
  
        t1.start();
        Thread.sleep(1000);  
  
        Thread t2 = new Thread(new Runnable() {  
            public void run() {  
                new ObjectAndClassLevelLocking().m2();  
            }  
        });
        t2.start();
        t1.join();
        t2.join();
    }  
  
    public static synchronized void m1() throws Exception {  
        // The following code is actually synchronized on Test.class object.  
        System.out.println("m1 is started");  
        Thread.sleep(2000);  
        System.out.println("m1 is completed");  
    }  
  
    public synchronized void m2() {  
        // This code is not synchronized.  
        System.out.println("m2 is working");  
    }  
}  