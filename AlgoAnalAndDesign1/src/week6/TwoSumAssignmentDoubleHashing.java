package week6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TwoSumAssignmentDoubleHashing {
	
	private static int size =  1000000;

	private static long[] numbers =  new long[size];
	
	private static Map<Integer, List<List<Long>>> nHashMap = new HashMap<Integer, List<List<Long>>>();
	
	private static Map<Integer, List<List<Long>>> pHashMap = new HashMap<Integer, List<List<Long>>>();
	
	private static Map<Integer, Boolean> count = new HashMap<Integer,Boolean>();
	
	public static void main (String args[]) throws IOException {
		readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 6\\ProgrammingAssignment\\algo1_programming_prob_2sum.txt");
		createHashMap();	
		countTwoSumPoss();
		System.out.println(count.size());
	}
	
	
	private static void countTwoSumPoss() {
		long startTime = System.currentTimeMillis();
		int additions = 0;
		for (Integer nKey : nHashMap.keySet()) {
			List <List<Long>> pChain = pHashMap.get(nKey * -1);
			if (pChain != null) {
				List<List<Long>> nChain = nHashMap.get(nKey);
				for (int i=0; i<5; i++) {
					List<Long> nElementsList = nChain.get(i);
					List<Long> pElementsList = pChain.get(i);
					if (nElementsList.size() > 0 && pElementsList.size() > 0) {
						for (Long nNumber : nElementsList) {
							for (Long pNumber : pElementsList) {
								additions++;
								int sum = (int) (nNumber + pNumber);
								if (sum >= -10000 && sum <= 10000) {
									count.put(sum, true);
								}
							}
						}
					}
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Additions done in:" + (endTime - startTime));
		System.out.println("Total additions are:" + additions);
	}
	
	private static void createHashMap() {
		long startTime = System.currentTimeMillis();
		for (int i=0; i<size; i++) {
			long element = numbers[i];
			int hashValue = (int) (element / 100000);
			if (hashValue < 0 || element < 0) {
				List<List<Long>> chain = nHashMap.get(hashValue);
				if (chain == null) {
					chain = new ArrayList<List<Long>>();
					chain.add(new LinkedList<Long>());
					chain.add(new LinkedList<Long>());
					chain.add(new LinkedList<Long>());
					chain.add(new LinkedList<Long>());
					chain.add(new LinkedList<Long>());
				} 
				int hashValue2 = (int) ((element % 100000) / 20000); 
				chain.get(hashValue2 * -1).add(element);
				nHashMap.put(hashValue, chain);
			} else {
				List<List<Long>> chain = pHashMap.get(hashValue);
				if (chain == null) {
					chain = new ArrayList<List<Long>>();
					chain.add(new LinkedList<Long>());
					chain.add(new LinkedList<Long>());
					chain.add(new LinkedList<Long>());
					chain.add(new LinkedList<Long>());
					chain.add(new LinkedList<Long>());
				} 
				int hashValue2 = (int) ((element % 100000) / 20000);
				chain.get(hashValue2).add(element);
				pHashMap.put(hashValue, chain);
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("HashMap created in:" + (endTime - startTime));
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
