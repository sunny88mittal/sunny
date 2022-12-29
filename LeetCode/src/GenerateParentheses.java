import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenerateParentheses {

	public List<String> generateParenthesis(int n) {
		Map<Integer, Set<String>> validParenthesisMap = new HashMap<Integer, Set<String>>();

		// Create parenthesis for n=1
		validParenthesisMap.put(1, getInitialParenthesis());

		// Generate parenthesis till n
		for (int i = 2; i <= n; i++) {
			Set<String> newParenthesisSet = new HashSet<String>();
			newParenthesisSet.addAll(addOpeningAndClosingParenthesis(validParenthesisMap.get(i - 1)));
			for (int j = 1; j <= i / 2; j++) {
				Set<String> inputParenthesisSet1 = validParenthesisMap.get(j);
				Set<String> inputParenthesisSet2 = validParenthesisMap.get(i - j);
				newParenthesisSet.addAll(generateParenthesisByCombining(inputParenthesisSet1, inputParenthesisSet2));
			}
			validParenthesisMap.put(i, newParenthesisSet);
		}

		// Get final parenthesis list
		List<String> finalParenthesisList = new ArrayList<String>();
		for (String parenthesis : validParenthesisMap.get(n)) {
			finalParenthesisList.add(parenthesis);
		}
		return finalParenthesisList;
	}

	private Set<String> generateParenthesisByCombining(Set<String> inputParenthesisSet1,
			Set<String> inputParenthesisSet2) {
		Set<String> newParenthesisSet = new HashSet<String>();
		for (String parenthesis1 : inputParenthesisSet1) {
			for (String parenthesis2 : inputParenthesisSet2) {
				newParenthesisSet.add(parenthesis1 + parenthesis2);
				newParenthesisSet.add(parenthesis2 + parenthesis1);
			}
		}
		return newParenthesisSet;
	}

	private Set<String> addOpeningAndClosingParenthesis(Set<String> inputParenthesis) {
		Set<String> newParenthesisSet = new HashSet<String>();
		for (String parenthesis : inputParenthesis) {
			newParenthesisSet.add("(" + parenthesis + ")");
		}
		return newParenthesisSet;
	}

	private Set<String> getInitialParenthesis() {
		Set<String> validParenthesis = new HashSet<String>();
		validParenthesis.add("()");
		return validParenthesis;
	}

	public static void main(String args[]) {
		GenerateParentheses obj = new GenerateParentheses();
		System.out.println(obj.generateParenthesis(4));
	}
}
