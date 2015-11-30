package SRM666;

public class GoodString {

	public String isGood(String s) {
	   while (true) {
		   String temp = s.replaceAll("ab", "");
		   if (temp.length() == 0) {
			   return "Good";
		   } else if (s.length() == temp.length()) {
			   return "Bad";
		   }
		   s = temp;
	   }
	}
	
	public static void main (String args[]) {
		GoodString obj = new GoodString();
		System.out.println(obj.isGood("ab"));
		System.out.println(obj.isGood("aab"));
		System.out.println(obj.isGood("aabb"));
		System.out.println(obj.isGood("ababab"));
		System.out.println(obj.isGood("abaababababbaabbaaaabaababaabbabaaabbbbbbbb"));
		System.out.println(obj.isGood("aaaaaaaabbbaaabaaabbabababababaabbbbaabbabbbbbbabb"));
	}
}
