import java.util.HashSet;
import java.util.Set;

public class Solution2521 {
	 public int distinctPrimeFactors(int[] nums) {
		 Set<Integer> distPrimes = new HashSet<Integer>();
		 
		 for (int i=0; i<nums.length; i++) {
			 int num = nums[i];
			 if (num  == 1) {
				 distPrimes.add(1);
			 }
			 for (int j=2; j<= num; j++) {
				 if (num % j == 0) {
					 distPrimes.add(j);
					 while (num % j ==0) {
						 num/=j;
					 }
				 }
			 }
		 }

		 return distPrimes.size();
	 }
}
