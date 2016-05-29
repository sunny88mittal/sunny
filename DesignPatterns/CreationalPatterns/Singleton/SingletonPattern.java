package Singleton;

public class SingletonPattern {

	//Volatile so that the threads see the correct value
	private volatile static SingletonPattern uniqueInstance;

	private SingletonPattern(){
		//Private
	}
	
	//Double check to improve efficiency
	public static SingletonPattern getInstance() {
		if (uniqueInstance ==  null) {
			synchronized (SingletonPattern.class) {
				//Check again by the time we acquire lock
				//some other thread might have created the instance
				if (uniqueInstance == null) {
					uniqueInstance = new SingletonPattern();
				}
			}
		}
		return uniqueInstance;
	}
}
