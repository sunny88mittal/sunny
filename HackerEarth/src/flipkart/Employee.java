package flipkart;

public class Employee {
	
	private int id;
	
	private String name;
	
	private int rating;
	
	private int salary;
	
	public Employee(int id, String name, int rating, int salary) {
		//Validate the arguments
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}
	
	public int getRating() {
		return rating;
	}
	
	public String toString() {
		return "ID :" + id + " Name: " + name; 
	}
}
