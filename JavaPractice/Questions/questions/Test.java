package questions;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main (String args[]) {
		List<Integer> integers =  new ArrayList<Integer>();
		integers.add(1);
		integers.add(2);
		
		List list = integers;
		list.add("abc");
		
		System.out.println(list);
	}
}
