public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int topFloor = cost.length + 1;
        int finalCost[] = new int[topFloor];

        for (int i=2; i<topFloor ;i++) {
            finalCost[i] = Math.min(finalCost[i-2] + cost[i-2], finalCost[i-1] + cost[i-1]);
        }

        return finalCost[cost.length];
    }
}
