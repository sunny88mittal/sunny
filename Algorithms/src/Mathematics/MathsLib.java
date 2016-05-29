package Mathematics;

import java.math.BigInteger;

public class MathsLib {

	public static BigInteger getCombinatorial(int N, int R) {
		if (R == 0)
			return new BigInteger("1");

		BigInteger numerator = new BigInteger("1");
		for (int i = 0; i < R; i++) {
			numerator = numerator.multiply(new BigInteger(N - i + ""));
		}

		BigInteger denominator = new BigInteger("1");
		for (int i = 1; i <= R; i++) {
			denominator = denominator.multiply(new BigInteger(i + ""));
		}

		return numerator.divide(denominator);
	}

	public static int getEulerToitentFunction(int n) {
		int result = n;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				result -= result / i;
			while (n % i == 0)
				n /= i;
		}
		if (n > 1)
			result -= result / n;
		return result;
	}

	public static BigInteger getFibonaaciTerm(int n) {
		BigInteger[][] matrix = new BigInteger[][] {
				{ new BigInteger("1"), new BigInteger("1") },
				{ new BigInteger("1"), new BigInteger("0") } };
		BigInteger[][] resultMatrix = getMatrixPow(matrix, n);
		return (resultMatrix[1][0].multiply(new BigInteger("1"))
				.add(resultMatrix[1][1].multiply(new BigInteger("1"))));
	}

	// Computes power for a square matrix
	private static BigInteger[][] getMatrixPow(BigInteger[][] matrix, int n) {
		if (n == 1) {
			return matrix;
		}

		BigInteger[][] matrixHalf = getMatrixPow(matrix, n / 2);
		BigInteger[][] resultMatrix = multiplyMatrices(matrixHalf, matrixHalf);
		if (n % 2 == 1) {
			resultMatrix = multiplyMatrices(resultMatrix, matrix);
		}
		return resultMatrix;
	}

	private static BigInteger[][] multiplyMatrices(BigInteger[][] matrixA,
			BigInteger[][] matrixB) {
		int size = matrixA.length;
		BigInteger[][] resultMatrix = new BigInteger[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				BigInteger sum = new BigInteger("0");
				for (int k = 0; k < size; k++) {
					sum = sum.add(matrixA[i][k].multiply(matrixB[k][j]));
				}
				resultMatrix[i][j] = sum;
			}
		}
		return resultMatrix;
	}
}
