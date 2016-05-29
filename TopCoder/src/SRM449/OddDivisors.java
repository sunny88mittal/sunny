package SRM449;

public class OddDivisors {
	
	public long findSum(int N) {
		if (N == 0) {
			return 0;
		}
		long noOfOddNos = N/2;
		if (N%2 != 0) {
			noOfOddNos += 1;
		}
		return (noOfOddNos)*(noOfOddNos) + findSum(N/2);
	}
}
