import java.util.*;

public class FourSum {
    private class Pair {
        int i,j;
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>>  ans = new ArrayList<>();
        Set<String> ansChecker = new HashSet<>();
        Map<Integer, List<Pair>> sumPairsListMap = new HashMap<>();

        int n =nums.length;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n;j++) {
                int sum = nums[i] + nums[j];
                Pair pair = new Pair();
                pair.i = i;
                pair.j = j;
                List<Pair> pairs = sumPairsListMap.get(sum);
                if (pairs == null) {
                    pairs = new ArrayList<Pair>();
                }
                pairs.add(pair);
                sumPairsListMap.put(sum, pairs);
            }
        }

        while (sumPairsListMap.size() > 0) {
            Object[] keys = sumPairsListMap.keySet().toArray();
            if (keys.length > 0) {
                int key = (int) keys[0];
                int sum = key;
                List<Pair> pairs = sumPairsListMap.remove(sum);
                if (pairs == null) {
                    continue;
                }
                List<Pair> matchingPair = sumPairsListMap.get(target - sum);
                
                if (target - key == key) {
                    if (matchingPair == null) {
                        matchingPair = new ArrayList<Pair>();
                    }
                    matchingPair.addAll(pairs);
                }

                if (matchingPair != null) {
                    for (Pair fPair : pairs) {
                        for (Pair sPair : matchingPair) {
                            if (fPair.i != sPair.i && fPair.i != sPair.j && fPair.j != sPair.i && fPair.j != sPair.j) {
                                List<Integer> result = new ArrayList<Integer>();
                                result.add(nums[fPair.i]);
                                result.add(nums[fPair.j]);
                                result.add(nums[sPair.i]);
                                result.add(nums[sPair.j]);
                                Collections.sort(result);
    
                                String str = result.toString();
                                if (!ansChecker.contains(str)) {
                                    ans.add(result);
                                    ansChecker.add(str);
                                }
                            }
                        }    
                    }
                }
            }   
        }

        return ans;
    }
}
