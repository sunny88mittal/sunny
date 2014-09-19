package Generics;

public class BoundsMultiple {
	
	static class A { /* ... */ };
	interface B { /* ... */ };
	interface C { /* ... */ };

	//A type variable with multiple bounds is a subtype of all the types listed in the bound. If one of the bounds is a class, it must be specified first
	class D <T extends A & B & C> { /* ... */ };

}
