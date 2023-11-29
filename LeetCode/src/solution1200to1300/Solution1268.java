package solution1200to1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1268 {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> ans = new ArrayList<>();
		Arrays.sort(products);

		int start = 0;
		for (int i = 1; i <= searchWord.length(); i++) {
			int count = 0;
			String subString = searchWord.substring(0, i);
			List<String> productsMatch = new ArrayList<>();
			boolean found = false;
			for (int j = start; j < products.length && count < 3; j++) {
				if (products[j].startsWith(subString)) {
					productsMatch.add(products[j]);
					++count;
					if (!found) {
						found = true;
						start = j;
					}
				}
			}
			ans.add(productsMatch);
		}

		return ans;
	}
}
