package Basics;

public class EqualsOperatorTest {

	public static void main (String args[]) {
		String str1 = new String("abcd");
		String str2 = new String("abcd");
		
		//Part1
	    if (str1 == str2) {
	    	System.out.println("Equal");
	    } else {
	    	System.out.println("Not Equal");
	    }
	    changeStrings(str1, str2);
	    System.out.println(str1);
	    System.out.println(str2);
	    
	    //Part2
	    str1 = "abcd";
		str2 = "abcd";
		if (str1 == str2) {
	    	System.out.println("Equal");
	    } else {
	    	System.out.println("Not Equal");
	    }
		changeStringsAgain(str1, str2);
	    System.out.println(str1);
	    System.out.println(str2);
	}
	
	
	private static void changeStrings(String s1,String s2) {
		s1 = "xyz";
		s2 = "abc"; 
	}
	
	private static void changeStringsAgain(String s1,String s2) {
		s1 = s1.replace('a', 'x');
		s2 = s2.replace('a', 'x'); 
	}
}
