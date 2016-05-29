package SRM649;

public class DecipherabilityEasy {

	public String check(String s, String t) {
		String POSSBILE = "Possible";
		String IMPOSSIBLE = "Impossible";
		
		if (s.length() - t.length() != 1) {
			return IMPOSSIBLE;
		}
		
		for (int i=0; i<s.length(); i++) {
			String ts = s.substring(0,i) + s.substring(i+1, s.length());
			if (ts.equals(t)) {
				return POSSBILE;
			}
		}
		
		return IMPOSSIBLE;
	}
	
	public static void main (String args[]) {
		DecipherabilityEasy obj = new DecipherabilityEasy();
		System.out.println(obj.check("abc", "ab"));
	}
}
