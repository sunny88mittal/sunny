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
				mergeSeq(map, nums[i] - 1, seq, -1);
				mergeSeq(map, nums[i] + 1, seq, 1);
				int count = seq.max - seq.min + 1;
				if (count > longestSequence) {
					longestSequence = count;
				}
			}
		}

		return longestSequence;
	}

	private void mergeSeq(Map<Integer, SequenceBuilder> map, int startAt, SequenceBuilder inputSeq, int direction) {
		while (map.containsKey(startAt)) {
			map.remove(startAt);
			if (direction == -1) {
				inputSeq.min = startAt;
				--startAt;
			} else {
				inputSeq.max = startAt;
				++startAt;
			}
		}
	}
}
