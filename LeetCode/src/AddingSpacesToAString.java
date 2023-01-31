
public class AddingSpacesToAString {
	public String addSpaces(String s, int[] spaces) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < spaces.length; i++) {
			int start = i > 0 ? spaces[i - 1] : 0;
			sb.append(s.subSequence(start, spaces[i]));
			sb.append(" ");
		}

		sb.append(s.subSequence(spaces[spaces.length - 1], s.length()));

		return sb.toString();
	}
}
