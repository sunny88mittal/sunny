package SRM660;

public class Cyclemin {

	public 	String bestmod(String s, int k) {
		int n = s.length();
		
		String min = s;
		for (int i=0; i<=n-1; i++) {
			//Rotate
			String temp = s.charAt(n-1) + s.substring(0, n-1);
			s = temp;
			
			//Replace
			char[] tempc = temp.toCharArray();
			int tk = k;
			for (int j=0; j<n && tk > 0; j++) {
				if (tempc[j] != 'a') {
					tempc[j] = 'a';
					tk--;
				}
			}
			temp = new String(tempc);
			
			//Compare
			if (temp.compareTo(min) < 0) {
				min = temp;
			}
		}
		
		return min;
	}
	
	public static void main (String args[]) {
		Cyclemin obj = new Cyclemin();
		System.out.println(obj.bestmod("aba",1));
		System.out.println(obj.bestmod("aba",0));
		System.out.println(obj.bestmod("bbb",2));
		System.out.println(obj.bestmod("sgsgaw",1));
		System.out.println(obj.bestmod("abacaba",1));
		System.out.println(obj.bestmod("isgbiao",2));
	}
}
