import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	private class Node {
		char c;
		Map<Character, Node> childNodes = null;
		List<String> terminatingStrings = null;
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> anagramGroups = new ArrayList<List<String>>();
		Node root = new Node();
		for (String str : strs) {
			char[] sortedString = str.toCharArray();
			Arrays.sort(sortedString);
			Node tempRoot = root;

			if (sortedString.length == 0) {
				if (tempRoot.terminatingStrings == null) {
					tempRoot.terminatingStrings = new ArrayList<String>();
					anagramGroups.add(tempRoot.terminatingStrings);
				}
				tempRoot.terminatingStrings.add(str);
			}

			for (int i = 0; i < sortedString.length; i++) {
				Map<Character, Node> childNodes = tempRoot.childNodes;
				if (childNodes == null) {
					childNodes = new HashMap<Character, Node>();
					tempRoot.childNodes = childNodes;
				}

				tempRoot = childNodes.get(sortedString[i]);
				if (tempRoot == null) {
					tempRoot = new Node();
					tempRoot.c = sortedString[i];
					childNodes.put(sortedString[i], tempRoot);
				}

				if (i == sortedString.length - 1) {
					if (tempRoot.terminatingStrings == null) {
						tempRoot.terminatingStrings = new ArrayList<String>();
						anagramGroups.add(tempRoot.terminatingStrings);
					}
					tempRoot.terminatingStrings.add(str);
				}
			}
		}

		return anagramGroups;
	}

	public static void main(String args[]) {
		GroupAnagrams obj = new GroupAnagrams();
		String[] input = new String[] { "" };
		System.out.println(obj.groupAnagrams(input));
	}
}
