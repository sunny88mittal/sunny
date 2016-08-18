package string;

public class Permutations {
	public static void main(String[] args) {
		String str = "ABCDE";
		printPermutations("", str);
		printPermutations(str.toCharArray(), 0, str.length() - 1);
	}

	// Recursive version
	// Problems : Unnecessary copies of data
	private static void printPermutations(String fixed, String variable) {
		if (variable.length() == 1) {
			System.out.println(fixed + variable);
			return;
		}

		for (int i = 0; i < variable.length(); i++) {
			String newFixed = fixed + variable.charAt(i);
			String newVariable = i > 0 ? variable.substring(0, i) : "";
			newVariable += variable.substring(i + 1, variable.length());
			printPermutations(newFixed, newVariable);
		}
	}

	// Recursive using backtracking
	private static void printPermutations(char[] string, int l, int r) {
		if (l == r) {
			System.out.println(new String(string));
			return;
		}

		for (int i = l; i <= r; i++) {
			swapChar(string, l, i);
			printPermutations(string, l + 1, r);
			swapChar(string, i, l);
		}
	}

	private static void swapChar(char[] string, int l, int r) {
		char temp = string[l];
		string[l] = string[r];
		string[r] = temp;
	}
}