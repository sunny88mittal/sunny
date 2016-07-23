package Basics;

public class StringsTest {

	public static void main (String args[]) {
		String s1 = "abcd";
		
		String s2 = "abcd";
		
		String s3 = new String("abcd");
		
		String s4 = "ab" + "cd";
		
		String s6 =  new String("cd");
		
		String s5 = "ab" + s6;
		
		System.out.println(s1 == s2);
		
		System.out.println(s1 == s3);
		
		System.out.println(s1 == s4);
		
		System.out.println(s1 == s5);
	}
}
