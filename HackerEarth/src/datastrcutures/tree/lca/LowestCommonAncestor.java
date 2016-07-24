package datastrcutures.tree.lca;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LowestCommonAncestor {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		int n = Integer.parseInt(scan.nextLine());
		if (n == 1) {
			System.out.println(0);
			return;
		}

		int[] parents = new int[n + 1];
		String[] tokens = scan.nextLine().split(" ");
		for (int i = 0; i < tokens.length; i++) {
			parents[i + 1] = Integer.parseInt(tokens[i].trim());
		}

		int m = Integer.parseInt(scan.nextLine());
		int[] nodes = new int[m];
		tokens = scan.nextLine().split(" ");
		for (int i = 0; i < tokens.length; i++) {
			nodes[i] = Integer.parseInt(tokens[i].trim());
		}

		Set<Integer> cParents = new HashSet<Integer>();
		int temp = nodes[0];
		int lowestParent = temp;
		cParents.add(temp);
		while (temp != 0) {
			temp = parents[temp];
			cParents.add(temp);
		}

		for (int i = 1; i < nodes.length; i++) {
            temp = nodes[i];
            while (true) {
            	if (cParents.contains(temp)) {
            		lowestParent = temp;
            		break;
            	} else {
            		temp = parents[temp];
            	}
            }
		}
		
		System.out.println(lowestParent);
	}
}
