package array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonkAndPowerOfTime {

	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		String[] callingOrder = scan.nextLine().split(" ");
		String[] idealOrder = scan.nextLine().split(" ");
		List<String> callingOrderList = new ArrayList<String>();
		for (String str : callingOrder) {
			callingOrderList.add(str);
		}
		
		int time = 0;
		for (int i=0; i<n; i++) {
			while (!idealOrder[i].equals(callingOrderList.get(0))) {
				String str = callingOrderList.remove(0);
				callingOrderList.add(str);
				time++;
			}
			callingOrderList.remove(0);
			time++;
		}
		
		System.out.println(time);
	}
}
