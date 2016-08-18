package string;

public class Permutations {
	public static void main(String[] args) {
		String str = "ABCDE";
		printPermutations("", str);
	}

	//Recursive version
	private static void printPermutations(String fixed, String variable) {
		if (variable.length() == 1) {
			System.out.println(fixed + variable);
			return;
		}

		for (int i = 0; i < variable.length(); i++) {
			String newFixed = fixed + variable.charAt(i);
			String newVariable = i > 0 ? variable.substring(0, i) : ""; 
			newVariable += variable.substring(i + 1, variable.length());
			printPermutations( newFixed,newVariable);
		}
	}
}
