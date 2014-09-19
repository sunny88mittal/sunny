package SRM148;

import java.util.ArrayList;
import java.util.List;

public class DivisorDigits {
	
	public int howMany(int number) {
		List<Integer> divisors = new ArrayList<Integer>();
		String str = "" + number;
		for (int i=0; i<str.length(); i++) {
			int div = Integer.parseInt(str.charAt(i) + "");
			if (div != 0 && number % div == 0) {
				divisors.add(div);
			}
		}
		return divisors.size();
	}
}
