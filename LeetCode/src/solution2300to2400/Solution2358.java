package solution2300to2400;

public class Solution2358 {
	public int maximumGroups(int[] grades) {
		// After sorting we need to combine 1,2,3...k elements till we have taken all
		// elements. So we have to solve the equation 1+2+3+... k <=n
		return (int) (Math.sqrt(grades.length * 2 + 0.25) - 0.5);
	}
}
