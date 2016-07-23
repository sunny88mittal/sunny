package Basics;

import java.util.Date;

class ImmutableClass {
	private final Date date;
	
	public ImmutableClass() {
		date = new Date();
	}
	
	public Date getDate(){
		return (Date) date.clone();
	}
}

public class FinalExp {
    public static void main (String args[]) throws InterruptedException {
    	ImmutableClass immutableClass = new ImmutableClass();
    	Thread.sleep(15000);
    	System.out.println(immutableClass.getDate());
    }
}
