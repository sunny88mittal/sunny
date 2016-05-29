package sokrati;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.DecimalFormat;

class NewContest {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int testCases = Integer.parseInt(line);
        for (int i = 0; i < testCases; i++) {
        	String[] tokens = br.readLine().split(" ");
            int S = Integer.parseInt(tokens[0]);
            int N = Integer.parseInt(tokens[1]);
            int M = Integer.parseInt(tokens[2]);
            int K = Integer.parseInt(tokens[3]);
            
            //If there are total less wins than min desired candidates
            if ( K > N) {  
            	System.out.println("0.000000");
            	continue;
            }
            
            //If desired candidates can always win
            int otherCandidates = S - M;
            int hisCandidatesWorstWin =  N - otherCandidates;
            if (hisCandidatesWorstWin >= K) {
            	System.out.println("1.000000");
            	continue;
            }
            
            //else we need to compute and it will be
            // while K<=N  sum {C(M,K) * C(S-M, N-K) / C(S,N)}
            BigInteger denominator = getCombinatorial(S, N);
            BigInteger inumerator = getCombinatorial(M, K).multiply(getCombinatorial(S-M, N-K));
            BigInteger totalNumerator = inumerator;
            K++;
            while (K <= N) {
            	
            	int toMultiply = M - (K - 1);
            	int toDivide = K;
            	inumerator = inumerator.multiply(new BigInteger("" + toMultiply));
            	inumerator = inumerator.divide(new BigInteger("" + toDivide));
            	
            	toMultiply = N - (K - 1);
            	toDivide = (S - M) - (N - K);
            	inumerator = inumerator.multiply(new BigInteger("" + toMultiply));
            	inumerator = inumerator.divide(new BigInteger("" + toDivide));
            	
            	totalNumerator = totalNumerator.add(inumerator);
            	K++;	
            }
            double result = totalNumerator.doubleValue() / denominator.doubleValue();
            DecimalFormat df=new DecimalFormat();
            df.setMinimumFractionDigits(6);
            df.setMaximumFractionDigits(6);
            System.out.println(df.format(result));
        }
    }
      
    private static BigInteger getCombinatorial(int N, int R) {
    	if (R == 0) return new BigInteger("1");
    	
    	BigInteger numerator = new BigInteger("1");
    	for (int i=0; i<R; i++) {
    		numerator = numerator.multiply(new BigInteger(N-i + ""));
    	}
    	
    	BigInteger denominator = new BigInteger("1");
    	for (int i=1; i<=R; i++) {
    		denominator = denominator.multiply(new BigInteger(i + ""));
    	}
    	
    	return numerator.divide(denominator);
    }
}

