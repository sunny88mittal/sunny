package sap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MicroAndInternship {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		// Read nodes
		int n = Integer.parseInt(scan.nextLine());
		String[] tokens = scan.nextLine().split(" ");
		Map<Integer, Node> nodes = new HashMap<Integer, Node>();
		for (int i = 0; i < n; i++) {
			int value = Integer.parseInt(tokens[i]);
			Node node = new Node(value);
			nodes.put(i + 1, node);
		}

		// Read links
		for (int i = 0; i < n - 1; i++) {
			tokens = scan.nextLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			Node nodea = nodes.get(a);
			Node nodeb = nodes.get(b);
			nodea.linkedNodes.add(nodeb);
			nodeb.linkedNodes.add(nodea);
		}

		// Read queries
		int m = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < n; i++) {
			tokens = scan.nextLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);

			Node nodea = nodes.get(a);
			Node nodeb = nodes.get(b);

			List<ComputeEntry> computeEntries = new LinkedList<ComputeEntry>();
			Set<Integer> values = new HashSet<Integer>();
			values.add(nodea.value);
			computeEntries.add(new ComputeEntry(nodea, values));

			while (computeEntries.size() > 0) {
				if (nodea == nodeb) {
					System.out.println(values.size());
					break;
				} else {
					ComputeEntry computeEntry = computeEntries.get(computeEntries.size() - 1);
					int doneTill = computeEntry.doneTill;
					if (doneTill >= computeEntry.node.linkedNodes.size() - 1) {
						computeEntries.remove(computeEntries.size() - 1);
					} else {
						computeEntry.doneTill = computeEntry.doneTill + 1;
						Node iNode = computeEntry.node.linkedNodes.get(computeEntry.doneTill);
						Set<Integer> ivalues = new HashSet<Integer>();
						ivalues.addAll(values);
						ivalues.add(iNode.value);
						computeEntries.add(new ComputeEntry(iNode, ivalues));
						nodea = iNode;
					}
				}
			}
		}
	}

	private static class Node {
		int value;
		List<Node> linkedNodes = new LinkedList<Node>();

		Node(int value) {
			this.value = value;
		}
	}

	private static class ComputeEntry {
		Node node;
		Set<Integer> values;
		int doneTill = -1;

		public ComputeEntry(Node node, Set<Integer> values) {
			this.node = node;
			this.values = values;
		}
	}
}
