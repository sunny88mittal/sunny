package sokrati;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class RoyAndRing {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		for (int i = 0; i < N; i++) {
			int point = Integer.parseInt(br.readLine());
			if (point <= 4) {
				System.out.println(1);
			} else {
				if (point % 2 != 0) {
					point += 1;
				}
				long fibTerm = getFibonaaciTerm(point / 2);
				System.out.println("Answer is:" + (fibTerm * fibTerm) % 1000007);
			}
		}
	}

	private static long getFibonaaciTerm(long n) {
       long[][] matrix = new long[][] {{1,1}, {1,0}}; 
       long[][] resultMatrix = getMatrixPow(matrix, n);
       return (resultMatrix[1][0]  +  resultMatrix[1][1]);
	}

	// Computes power for a square matrix
	private static long[][] getMatrixPow(long[][] matrix, long n) {
		
		if (n == 1) {
			return matrix;
		}
		
		long[][] matrixHalf = getMatrixPow(matrix, n / 2);
		long[][] resultMatrix = multiplyMatrices(matrixHalf, matrixHalf);
		if (n %2 == 1) {
			resultMatrix = multiplyMatrices(resultMatrix, matrix);
		}
		return resultMatrix;
	}

	private static long[][] multiplyMatrices(long[][] matrixA, long[][] matrixB) {
		int size = matrixA.length;
		long[][] resultMatrix = new long[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				long sum = 0;
				for (int k = 0; k < size; k++) {
					sum = sum + matrixA[i][k] * (matrixB[k][j]);
					sum %= 1000007;
				}
				resultMatrix[i][j] = sum;
			}
		}
		return resultMatrix;
	}
}
