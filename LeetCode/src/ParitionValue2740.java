import java.util.Arrays;

public class ParitionValue2740 {
    public int findValueOfPartition(int[] nums) {
        int ans = Integer.MAX_VALUE;
    
        Arrays.sort(nums);
    
        for (int i = 1; i < nums.length; ++i)
          ans = Math.min(ans, nums[i] - nums[i - 1]);
    
        return ans;
    }
}
