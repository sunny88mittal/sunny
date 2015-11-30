package SRM663;


public class ABBA {

	public String canObtain(String initial, String target) {
		while(target.length() != initial.length()) {
			int n = target.length();
			char ch = target.charAt(n-1);
			target = target.substring(0, n-1);
			if (ch == 'B') {
				target = reverseString(target);
			}
		}
		
		if (initial.equals(target)) {
			return "Possible";
		}
		
		return "Impossible";
	}
	
	private String reverseString(String s) {
		char[] ch = s.toCharArray();
		int n = ch.length;
		for (int i=0; i<n/2; i++) {
			char temp = ch[i];
			ch[i] = ch[n-i-1];
			ch[n-i-1] = temp;
		}
		return new String(ch);
	}
	
	public static void main (String args[]) {
		ABBA obj = new ABBA();
		System.out.println(obj.canObtain("B", "ABBA"));
		System.out.println(obj.canObtain("AB", "ABB"));
		System.out.println(obj.canObtain("BBAB", "ABABABABB"));
		System.out.println(obj.canObtain("BBBBABABBBBBBA", "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));
		System.out.println(obj.canObtain("A", "BB"));
	}
}
