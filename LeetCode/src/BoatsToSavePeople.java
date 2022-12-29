import java.util.Arrays;

public class BoatsToSavePeople {
	public int numRescueBoats(int[] people, int limit) {
		Arrays.sort(people);
		int lp = 0;
		int rp = people.length - 1;
		int boats = 0;
		while (lp <= rp) {
			++boats;
			if (lp == rp) {
				break;
			} else if (people[lp] + people[rp] <= limit) {
				++lp;
				--rp;
			} else {
				--rp;
			}
		}

		return boats;
	}

	public static void main(String args[]) {
		int[] people = new int[] { 3, 2, 2, 1 };
		int limit = 3;
		BoatsToSavePeople obj = new BoatsToSavePeople();
		obj.numRescueBoats(people, limit);
	}
}
