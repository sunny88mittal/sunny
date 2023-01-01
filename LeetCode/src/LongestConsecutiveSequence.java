import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

	class SequenceBuilder {
		int min;
		int max;

		public SequenceBuilder(int min, int max) {
			super();
			this.min = min;
			this.max = max;
		}
	}

	public int longestConsecutive(int[] nums) {
		int longestSequence = 0;
		Map<Integer, SequenceBuilder> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			SequenceBuilder seq = new SequenceBuilder(nums[i], nums[i]);
			map.put(nums[i], seq);
		}

		for (int i = 0; i < nums.length; i++) {
			SequenceBuilder seq = map.get(nums[i]);
			if (seq != null) {
				while (map.containsKey(seq.min - 1)) {
					map.remove(seq.min - 1);
					--seq.min;
				}

				while (map.containsKey(seq.max + 1)) {
					map.remove(seq.max + 1);
					++seq.max;
				}

				int count = seq.max - seq.min + 1;
				if (count > longestSequence) {
					longestSequence = count;
				}
			}
		}

		return longestSequence;
	}
}
