package SRM586;

public class PiecewiseLinearFunctionDiv2 {
	
	public int[] countSolutions(int[] Y, int[] query) {
		int length = query.length;
		int[] answer =  new int[length];
		
		for (int i=0; i<length; i++) {
			int c = query[i];
			int a = Y[0];
			for (int j=1; j < Y.length; j++) {
				int b = Y[j];
				
				if (c == a && c == b) {
					answer[i] = -1;
					break;
				}
				
				if ((c == a) ||(c > a && c < b) || (c > b && c < a)){
					answer[i]++;
					a=b;
					continue;
				}
				
				if (c == b){
					answer[i]++;
					if (j+1 < Y.length) {
						a = Y[j+1]; 
					}
					continue;
				}
			}
		}
		
		return answer;
 	}

}
