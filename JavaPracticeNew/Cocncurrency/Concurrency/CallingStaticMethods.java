package Concurrency;

import java.util.Date;

class A {
	public static synchronized void methodA() {
		System.out.println(new Date(System.currentTimeMillis()));
		System.out.println("MethodA");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void methodB() {
		System.out.println(new Date(System.currentTimeMillis()));
		System.out.println("MethodB");	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class B implements Runnable {
    A a;
	
	B(A a) {
	 this.a = a;	
	}
	
	@Override
	public void run() {
		a.methodA();
	}
}

class C implements Runnable {
    A a;
	
	C(A a) {
	 this.a = a;	
	}
	
	@Override
	public void run() {
		a.methodB();
	}
}

public class CallingStaticMethods {
     public static void main (String args[]) {
    	 A a = new A();
    	 Thread threadB = new Thread(new B(a));
    	 Thread threadC = new Thread(new C(a));
    	 threadB.start();
    	 threadC.start();
     }
}
