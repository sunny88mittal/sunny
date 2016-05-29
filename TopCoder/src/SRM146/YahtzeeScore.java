package SRM146;

public class YahtzeeScore {

	public int maxPoints(int[] toss) {
		int max = 0;
		int length = 7;
		int countSort[] = new int[length];
		for (int i=0; i<toss.length; i++){
			countSort[toss[i]]++;
		}
		
		for (int j=1; j<length; j++){
			int sum = countSort[j] *j;
			if (sum > max) {
				max = sum;
			}
		}
		
		return max;
	}
}
