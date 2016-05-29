package SRM663;


public class ABBA {

	public String canObtain(String initial, String target) {
		int start = 0;
		int end = target.length();
		boolean reverse = false;
		char[] ts = target.toCharArray();
		while (end - start > initial.length()) {
			if (!reverse) {
				if (ts[end-1] == 'B') {
					reverse = true;
				}
				end--;
			} else {
				if (ts[start] == 'B') {
					reverse = false;
				}
				start++;
			}
		}
		
		target = target.substring(start, end);
		if (reverse) {
			target = reverseString(target);
		}
		
		return initial.equals(target) ? "Possible" : "Impossible";
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
