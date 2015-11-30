package SRM666;

public class GoodString {

	public String isGood(String s) {
	   char ch[] = s.toCharArray();
	   int count = 0;
	   for (char c : ch){
		   if (c == 'a') {
			   count++;
		   } else {
			   if (count == 0) {
				   return "Bad";
			   }
			   count--;
		   }
	   }
	   
	   if (count !=0) {
		   return "Bad";
	   }
	   
	   return "Good";
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
