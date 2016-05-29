package Basics;

public class Polymorphism {
	
	static class Parent {
		public int x = 0;
		
		public void print() {
			System.out.println("Parent");
		}
		
		public static void staticPrint() {
			System.out.println("Static Parent");
		}
	}
	
	static class Child extends Parent {
		public int x = 1;
		
		public void print() {
			System.out.println("Child");
		}
		
		public static void staticPrint() {
			System.out.println("Static Child");
		}
	}
	
	public static void main (String args[]) {
		Parent parent = new Child();
		System.out.println(parent.x); //Should call parent compile time polymorphism
		parent.print(); //Should call child run time polymorphism
	    parent.staticPrint(); //Should call parent compile time polymorphism
	}
}
