package SRM652;

public class ValueOfString {

	public int findValue(String s) {
	   int result = 0;
	   int[] count = new int[27];
	   char[] cs = s.toCharArray();
	   for (char ch : cs) {
		   count[ch - 'a' + 1] ++;
	   }
	   int sum = 0;
	   for (int i=1; i<=26; i++) {
		   sum += count[i];
		   result += sum * count[i] * i;
	   }
	   return result;
	}
}
