package sokrati;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class PythagoreanTriplets {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] statusArray =  new int[n+1]; //0 not checked, 1 it has, 2 it does not 
            for (int j=2; j<=n; j++) {
            	//Continue in the array till number exists for which the status has not been set
            	if (statusArray[j] == 0) {
            		//Check if a number has a triplet
            		int hasTriplet = numberHasTriplet(j);
            		 //Mark all its multiples accordingly
            		updateStatusAray(statusArray, j, hasTriplet);
            	}
            }
            
            int count = 0;
            for (int j=0; j<=n; j++) {
            	if (statusArray[j] == 1) {
            		count++;
            	}
            }
            System.out.println(count);
        }
    }
     
    private static int numberHasTriplet(int n) {
    	int square = n*n;
    	for (int i=n-1; i>1; i--) {
    		int toEvaluate = square - i*i;
        	double upper = Math.ceil(Math.sqrt(toEvaluate));
        	double lower = Math.floor(Math.sqrt(toEvaluate));
        	if (lower == upper ) {
        		int[] arr = new int[] {n, i, (int)lower};
        		Arrays.sort(arr);
        		System.out.println(arr[0] +"," + arr[1] + "," + arr[2]);
        		return 1;
        	}
    	}
    	return 2;
    }
    
    private static void updateStatusAray(int[] statusArray, int n, int status) {
    	int i=n;
    	while (i <= n) {
    		statusArray[i] = status;
    		i += n;
    	}
    }
}