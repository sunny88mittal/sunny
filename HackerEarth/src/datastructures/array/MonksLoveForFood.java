package datastructures.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonksLoveForFood {

	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		List<String> price =  new ArrayList<String>();
		int n = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<n; i++) {
			String str = scan.nextLine();
			if (str.startsWith("1")) {
				if (price.isEmpty()) {
					System.out.println("No Food");
				} else {
					System.out.println(price.remove(0));
				}
			} else {
				price.add(0, str.split(" ")[1]);
			}
		}
	}
}
