package Basics;

public class OperatorBasics {

	public static void main(String args[]) {
		if (compare1() && compare2()) {
            System.out.println("and");
		}

		if (compare2() || compare1()) {
			System.out.println("or");
		}
		
		if (compare1() & compare2()) {
            System.out.println("and");
		}

		if (compare2() | compare1()) {
			System.out.println("or");
		}
	}

	private static boolean compare1() {
		System.out.println("compare1");
		return false;
	}

	private static boolean compare2() {
		System.out.println("compare2");
		return true;
	}
}
