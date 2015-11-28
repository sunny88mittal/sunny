package SRM658;

public class InfiniteString {

	public String equal(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        if (sn == tn && s.equals(t)) {
        	return "Equal";
        }
        
        int cn = sn * tn / getGCD(sn, tn);
        String sf = s;
        while (sf.length() != cn) {
        	sf += s;
        }
        String tf = t;
        while (tf.length() != cn) {
        	tf += t;
        }
        if (sf.equals(tf)) {
        	return "Equal";
        }
        
        return "Not equal";
	}
	
	private int getGCD(int a, int b) {
		int r = -1;
		while (true) {
			r = b % a;
			if (r == 0) {
				break;
			}
			b = a;
			a = r;
		}
		
		return a;
	}
	
	public static void main (String args[]) {
		InfiniteString obj = new InfiniteString();
		System.out.println(obj.equal("aaaaa","aaaaaa"));
	}
}
