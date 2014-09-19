package week3;

public class XOR2HexStrings {
	
	
	public static void main (String args[]) {
		String s1 = "83876d218f01a11d0769acb85294cb35";
		String s2 = "e56e26f5608b8d268f2556e198a0e01b";
		
		int length = s1.length();
		String str = "";
		for (int i=0; i<length/2; i++) {
			int a = Integer.parseInt(s1.substring(2*i, 2*i+2), 16);
			int b = Integer.parseInt(s2.substring(2*i, 2*i+2), 16);
			System.out.println(i + ":" + Integer.toHexString(a^b));
			str += Integer.toHexString(a ^ b);
		}
		
		System.out.println(str);
	}
}
