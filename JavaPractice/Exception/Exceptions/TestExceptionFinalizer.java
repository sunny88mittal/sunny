package Exceptions;

public class TestExceptionFinalizer {

	
	private static int testExceptionFinalizer () {
		try {
			throw new Exception();
		} catch (Exception e) {
			return 2;
		} finally {
			return 3;
		}
	}
	
	public static void main (String args[]) {
		System.out.println(testExceptionFinalizer());
	}
}
