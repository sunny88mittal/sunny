public class PredictTheWinner {
    public long getSum (int nums[], int start, int end, int turn) {
        if (start > end) {
            return 0;
        }

        if (turn == 0) {
            return Math.max(nums[start] + getSum(nums, start+1, end, 1), nums[end] + getSum(nums, start, end-1, 1));
        }

        return Math.min(getSum(nums, start+1, end, 0), getSum(nums, start+1, end, 0));
    }
    
    public boolean predictTheWinner(int[] nums) {
        long sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum += num;
        }

        long sumOne = getSum(nums, 0, n-1, 0);
        long sumTwo = sum - sumOne;

        return sumOne - sumTwo >= 0;
    }
}
