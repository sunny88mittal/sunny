package SRM670;

public class Drbalance {

	public int lesscng(String s, int k) {
		int negativity = getNegativityCount(s);
		if (negativity < k) {
			return 0;
		}
		
		int count = 0;
		while (negativity > k) {
			count++;
			s = s.replaceFirst("-", "+");
			negativity = getNegativityCount(s);
		}
		return count;
	}
	
	private int getNegativityCount(String s){
		int count = 0;
		for (int i=0; i<=s.length(); i++) {
			if (negativity(s.substring(0, i)) < 0) {
				count++;
			}
		}
		return count;
	}
	
	private int negativity(String s) {
		int plusCount = 0;
		int minCount = 0;
		for (int i=0; i<s.length(); i++) {
			switch(s.charAt(i)) {
			case '+':
				plusCount++;
				break;
			case '-':
				minCount++;
				break;
			}
		}
		return plusCount - minCount;
	}
	
	public static void main(String args[]) {
		Drbalance obj = new Drbalance();
		System.out.println(obj.lesscng("---", 1));
		System.out.println(obj.lesscng("+-+-", 0));
		System.out.println(obj.lesscng("-+-+---", 2));
		System.out.println(obj.lesscng("-------++", 3));
		System.out.println(obj.lesscng("-+--+--+--++++----+", 3));
	}
}
