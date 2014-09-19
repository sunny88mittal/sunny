package SRM570;

//http://community.topcoder.com/stat?c=problem_statement&pm=12424&rd=15490

public class Chopsticks {

	public int getmax(int[] length) {
		int max = 0;
		int[] chopSizeCount = new int[101];
		for (int i : length) {
			chopSizeCount[i]++; 
		}
		
		for (int i=1; i<101; i++) {
			max =  max + chopSizeCount[i] /2;
		}
		
		return max;
	}	
}
