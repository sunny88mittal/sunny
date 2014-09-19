package week6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SAT2 {

	private static int size = 1000000;
	
	private static boolean currentValues[] = new boolean[size+1];
	
	private static Map<Integer, List<SAT2Condition>> conditions = new ConcurrentHashMap<Integer, List<SAT2Condition>>();
	
	private static Map<Integer, Integer> unsatisfiedCondCountMap = new LinkedHashMap<Integer, Integer>();
	
	private static int changedElement = -1;
	
	private static int unsatisfiedCondCount = 0;
	
	public static void main (String args[]) throws IOException {
		read2SATConditions("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 6\\Assignment\\2sat6.txt");
		System.out.println("Initial size is:" + conditions.size());
		preProcessData();
		size = conditions.size();
		System.out.println("New size is:" + size);
		papadimitrousAlgo();
	}
	
	
	private static void preProcessData() {
		boolean improving = true;
		int startSize = conditions.size();
		int endSize = conditions.size();;
		while (improving) {
			improving = false;
			startSize =  endSize;
			//Remove the list entries from map which are all same
			for (Entry<Integer, List<SAT2Condition>> conditionsEntrySet : conditions.entrySet()) {
				int key = conditionsEntrySet.getKey();
				int negativeCount = 0;
				int positiveCount = 0;
				for (SAT2Condition condition : conditionsEntrySet.getValue()) {
					if (negativeCount > 0 && positiveCount > 0) {
						break;
					}
					int a = condition.a;
					int b = condition.b;
					
					if (Math.abs(a) == key) {
						if (a > 0) {
							positiveCount++;
						} else {
							negativeCount++;
						}
					} else {
						if (b > 0) {
							positiveCount++;
						} else {
							negativeCount++;
						}
					}
				}
				
				if (!(negativeCount > 0 && positiveCount > 0)){
					if (negativeCount > 0) {
						currentValues[key] = false;
					} else {
						currentValues[key] = true;
					}
					
					for (SAT2Condition condition : conditionsEntrySet.getValue()) {
						condition.satisfied = true;
					}
					conditions.remove(key);
				}
			}
			
			//Remove the satisfied conditions
			for (Entry<Integer, List<SAT2Condition>> conditionsEntrySet : conditions.entrySet()) {
				List<SAT2Condition> toRemove = new ArrayList<SAT2Condition>();
				for (SAT2Condition condition : conditionsEntrySet.getValue()) {
					if (condition.satisfied) {
						toRemove.add(condition);
					}
				}
				conditionsEntrySet.getValue().removeAll(toRemove);
			}
			
			endSize = conditions.size();
						
			if (endSize < startSize) {
				improving = true;
			}
		}
	}
	
	
	private static void papadimitrousAlgo() {
		for (int i=0; i<Math.log(size); i++) {
			System.out.println("I is:" + i);
			computeRandomAssignment();
			checkAllConditions();
			long cycles = (long)(2*size)*(long)size;
			long startTime = System.currentTimeMillis();
			for (long j=0; j<cycles; j++) {
				if (j%100000 == 0) {
					System.out.println("J is:" + j);
					System.out.println(System.currentTimeMillis() - startTime);
					startTime = System.currentTimeMillis();
				
				}
				
				//Check if all conditions are satisfied
				if (unsatisfiedCondCount  == 0) {
					System.out.println("Satisfiable");
					return;
				}
				
				//Get a random unsatisfied condition
				SAT2Condition cond = getRandomUnsatisfiedCond();
				
				//Flip the value of one of the conditions element randomly
				Random ran = new Random();
				int toChange = cond.b;
				if (ran.nextBoolean()) {
					toChange = cond.a;
				}
				toChange = Math.abs(toChange);
				currentValues[toChange] = !currentValues[toChange];
				
				changedElement = toChange;
				
				//Update the data structures
				updateMaps();
			}
		}
		
		System.out.println("UnSatisfiable");		
	}
	
	
	private static void updateMaps() {
		List<SAT2Condition> conditionsList = conditions.get(changedElement);
		for (SAT2Condition condition : conditionsList) {
			int a = condition.a;
			int b = condition.b;
			
			boolean aValue = currentValues[Math.abs(a)];
			boolean bValue = currentValues[Math.abs(b)];
			if (a < 0) {
				aValue = !aValue;
			}
			
			if (b < 0) {
				bValue = !bValue;
			}
			
			a = Math.abs(a);
			b = Math.abs(b); 
			
			boolean isEarlierSatisfied = condition.satisfied;
			
			if (aValue || bValue) {
				condition.satisfied = true;
				if (!isEarlierSatisfied) {
					unsatisfiedCondCount = unsatisfiedCondCount - 2;
					
					int unsatisfiedconds = unsatisfiedCondCountMap.get(a) - 1;
					if (unsatisfiedconds == 0) {
						unsatisfiedCondCountMap.remove(a);
					} else {
						unsatisfiedCondCountMap.put(a, unsatisfiedconds);	
					}
					
					unsatisfiedconds = unsatisfiedCondCountMap.get(b) - 1;
					if (unsatisfiedconds == 0) {
						unsatisfiedCondCountMap.remove(b);
					} else {
						unsatisfiedCondCountMap.put(b, unsatisfiedconds);	
					}
				}
			} else {
				condition.satisfied = false;
				if (isEarlierSatisfied) {
					unsatisfiedCondCount = unsatisfiedCondCount + 2;
					if (unsatisfiedCondCountMap.get(a) ==  null) {
						unsatisfiedCondCountMap.put(a, 0);
					}
					
					if (unsatisfiedCondCountMap.get(b) ==  null) {
						unsatisfiedCondCountMap.put(b, 0);
					}
					
					unsatisfiedCondCountMap.put(a, unsatisfiedCondCountMap.get(a) + 1);
					unsatisfiedCondCountMap.put(b, unsatisfiedCondCountMap.get(b) + 1);
				}
			}
		}
	}
	
	private static void printCurrentAssignments() {
		String output = new String();
		for (int i=1; i<=size; i++) {
			output += currentValues[i];
		}
		
		System.out.println(output);
	}
	
	/**
	 * To get random unsatisfied condition
	 * @return
	 */
	private static SAT2Condition getRandomUnsatisfiedCond() {
		int size = unsatisfiedCondCountMap.size();
		Random ran = new Random();
		int randomNo = ran.nextInt(size);
		int indexToChoose = -1;
		int i=0;
		for (Integer abc : unsatisfiedCondCountMap.keySet()){
			indexToChoose = abc;
			i++;
			if (i > randomNo) {
				break;
			}
		}
		
		List<SAT2Condition> conditionsList = conditions.get(indexToChoose);
		SAT2Condition condition = null;
		for (SAT2Condition conditioni : conditionsList) {
			if (!conditioni.satisfied) {
				condition = conditioni;
			}
		}
		return condition;
	}
	
	/**
	 * Method to compute the initial random assignment
	 */
	private static void computeRandomAssignment() {
		Random ran = new Random(1);
		for (int i=0; i<=size; i++) {
			currentValues[i] = ran.nextBoolean();
		}
	}
	
	/**
	 * Method to create the initial unsatisfied conditions count
	 */
	private static void checkAllConditions() {
		unsatisfiedCondCountMap.clear();
		unsatisfiedCondCount = 0;
		
		for (Entry<Integer, List<SAT2Condition>> conditionsEntrySet : conditions.entrySet()) {
			int key = conditionsEntrySet.getKey();
			for (SAT2Condition condition : conditionsEntrySet.getValue()) {
				int a = condition.a;
				int b = condition.b;
				int absa = Math.abs(a);
				int absb = Math.abs(b);
				
				
				boolean aValue = currentValues[absa];
				boolean bValue = currentValues[absb];
				if (a < 0) {
					aValue = !aValue;
				}
				
				if (b < 0) {
					bValue = !bValue;
				}
				
				
				if (!(aValue || bValue)) {
					unsatisfiedCondCount++;
					condition.satisfied = false;
					
					if (unsatisfiedCondCountMap.get(key) ==  null) {
						unsatisfiedCondCountMap.put(key, 0);
					}
					unsatisfiedCondCountMap.put(key, unsatisfiedCondCountMap.get(key) + 1);
				} else {
					condition.satisfied = true;
				}
			}
		}
	}
	
	/**
	 * Method to read data
	 * @param filePath
	 * @throws IOException
	 */
	private static void read2SATConditions (String filePath) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filePath));
		String line = in.readLine();
		while (line != null) {
			String[] tokens = line.split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			SAT2Condition condition = new SAT2Condition(a, b);
			a = Math.abs(a);
			b = Math.abs(b);
			
			List<SAT2Condition> aList = conditions.get(a);
			List<SAT2Condition> bList = conditions.get(b);
			
			if (aList == null) {
				aList = new ArrayList<SAT2Condition>(); 
				conditions.put(a, aList);
			}
			
			if (bList == null) {
				bList = new ArrayList<SAT2Condition>(); 
				conditions.put(b, bList);
			}
			
			aList.add(condition);
			bList.add(condition);
			
			line = in.readLine();
		}
		in.close();
	}
	
	/**
	 * Class to represent the 2 SAT condition
	 * @author sumittal
	 *
	 */
	private static class SAT2Condition {
		int a;
		int b;
		boolean satisfied = false;
		
		public SAT2Condition(int a, int b) {
			this.a=a;
			this.b=b;
		}
	}
}
