package introduction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DNASorting {

	public static void main(String args[]) throws Exception {
		Scanner cin = new Scanner(System.in);
		List<String> strings = new ArrayList<String>();
		final Map<String, Integer> measure = new HashMap<String, Integer>();
		int n = cin.nextInt();
		int m = cin.nextInt();
		cin.nextLine();
		for (int i = 0; i < m; i++) {
			strings.add(cin.nextLine());
		}

		for (String str : strings) {
			int count = 0;
			char temp[] = str.toCharArray();
			n = temp.length;
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (temp[i] > temp[j]) {
						count++;
					}
				}
			}
			measure.put(str, count);
		}

		Collections.sort(strings, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return measure.get(o1).compareTo(measure.get(o2));
			}
		});

		for (String str : strings) {
			System.out.println(str);
		}
	}
}