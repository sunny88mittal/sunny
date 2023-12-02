package solution1600to1700;

public class Solution1678 {
	public String interpret(String command) {
		return command.replace("()", "o").replace("(al)", "al");
	}
}
