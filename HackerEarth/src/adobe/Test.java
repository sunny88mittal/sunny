package adobe;

public class Test {

	static int value = 1;

	public int getValue() {

		try {

			value = value + 1;

			return value;

		}

		catch (Exception e) {

			return 0;

		}

		finally {

			value = value + 1;

		}

	}

	public static void main(String[] args) {
		
		Test t1 = new Test();

		System.out.println("Value of variable = " + t1.getValue());

		System.out.println("Value of variable = " + t1.value);
		

	}

}