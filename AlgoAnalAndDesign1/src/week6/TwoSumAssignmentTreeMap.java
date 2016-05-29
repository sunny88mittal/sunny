package week6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TwoSumAssignmentTreeMap {
	
	private static int size =  1000000;

	private static long[] numbers =  new long[size];
		
	private static TreeMap<Long,Long> treeMap = new TreeMap<Long, Long>();
	
	private static Map<Integer, Boolean> count = new HashMap<Integer,Boolean>();
	
	public static void main (String args[]) throws IOException {
		readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 6\\ProgrammingAssignment\\algo1_programming_prob_2sum.txt");
		System.out.println("Data Read");
		createHashMap();
		System.out.println("Hash Map Created");
        countTwoSumPoss();
		System.out.println(count.size());
	}
	
	
	private static void countTwoSumPoss() {
		for (int j=0; j<size; j++) {
			long number = numbers[j];
			Map<Long, Long> subMap = treeMap.subMap(-10000-number, true, 10000-number, true);
			for (Long key : subMap.keySet()) {
				count.put((int) (number + key), true);
			}
		}
	}
	
	private static void createHashMap() {
		for (int i=0; i<size; i++) {
			treeMap.put(numbers[i], numbers[i]);
		}
	}
	
	private static void readFile(String filePath) throws IOException {
		BufferedReader in = null;
		int i=0;
		try {
			in = new BufferedReader(new FileReader(filePath));
			String line = in.readLine();
			while (line != null) {
				numbers[i] = Long.parseLong(line.trim());
				i++;
				line = in.readLine();
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
