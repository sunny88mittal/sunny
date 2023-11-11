
public class Solution2522 {
	public int minimumPartition(String s, int k) {
		int count =0;
		int start =0;
		while (start < s.length()) {
			int val = s.charAt(start) - '0';
			if (val > k) {
				return -1;
			}
			++count;
			boolean found = false;
			while (val <=k && start +  1< s.length()) {
				++start;
				found = true;
				int nextDigit = s.charAt(start) - '0';
				val = val*10 + nextDigit;
			}
			
			if (!found) {
				++start;
			}
		}
		return count;
		
    }
	
	public static void main (String args[]) {
		Solution2522 sol =  new Solution2522();
		sol.minimumPartition("2995624428278123422153476983795874268215311982758939386251", 128);
	}
}
