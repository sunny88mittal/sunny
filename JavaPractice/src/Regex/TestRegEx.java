package Regex;

public class TestRegEx {

	static String regex = "(.*:\\S+\\s?)+";
	
	public static void main (String args[]) {
		/*String str = "";
		String str1 = ":Native"; //correct                   
		String str2 = "user1:Native user2:abc"; //correct
		String str3 = "user1:Native  :abc"; //correct
		String str4 = "user1:  :abc"; //Incorrect
		String str5 = "user1:Native user2:  user3:Native";//Incorrect
		
		System.out.println(str.matches(regex));
		System.out.println(str1.matches(regex));
		System.out.println(str2.matches(regex));
		System.out.println(str3.matches(regex));
		System.out.println(str4.matches(regex));
		System.out.println(str5.matches(regex));
		
		String[] tokens = ":".split(":");
		
		System.out.println("Token 1:" + tokens[0]);
		System.out.println("Token 2:" + tokens[1]);
		
		regex = "(\\S+\\s?)+";
		System.out.println("abc ".matches(regex));
		System.out.println("abcd".matches(regex));
		System.out.println("abc d".matches(regex));
		System.out.println("abcd  ".matches(regex));*/
		
		
		System.out.println("user1".matches("[a-z]*[1-9]*")); 
	}
}
