
public class PartitionStringIntoSubstringsWithValuesAtMostK {
	public int minimumPartition(String s, int k) {
		int partitionCount = 0;

		long num = 0;
		int i = 0;
		while (i < s.length()) {
			int start = i;
			while (num < k && i < s.length()) {
				num = num * 10 + Integer.parseInt(s.charAt(i) + "");
				++i;
			}

			if (num > k) {
				--i;
			}

			if (start == i) {
				return -1;
			}

			++partitionCount;
			num = 0;
		}

		return partitionCount;
	}

	public static void main(String args[]) {
		PartitionStringIntoSubstringsWithValuesAtMostK obj = new PartitionStringIntoSubstringsWithValuesAtMostK();
		obj.minimumPartition("238182", 5);
	}
}
