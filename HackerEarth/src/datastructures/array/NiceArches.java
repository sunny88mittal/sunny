package datastructures.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NiceArches {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		int count = 0;
		for (int i=0; i<n; i++) {
			if (isBubbly(scan.nextLine())) {
				count++;
			}
		}
		System.out.println(count);
	}

	private static boolean isBubbly(String str) {
		List<Character> stack = new ArrayList<Character>();
		stack.add(0, str.charAt(0));
		for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (stack.size() == 0) {
            	stack.add(0, ch);
            	continue;
            }
            
            if (stack.get(0) == ch) {
            	stack.remove(0);
            } else {
            	stack.add(0, ch);
            }
		}
		return stack.size() == 0;
	}
}
