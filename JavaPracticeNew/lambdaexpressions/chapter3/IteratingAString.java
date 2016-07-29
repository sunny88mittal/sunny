package chapter3;

public class IteratingAString {

	//Iterate a string
	public static void main(String args[]) {
		final String str = "teststring";
		
		//lambda expression
		str.chars().forEach(ch -> System.out.println(ch));
		
		//Method reference
		str.chars().forEach(System.out :: println);
	}
}
