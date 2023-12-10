package solution600to700;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution690 {
	class Employee {
		public int id;
		public int importance;
		public List<Integer> subordinates;
	}

	public int getImportance(List<Employee> employees, int id) {
		Set<Integer> seen = new HashSet<>();
		Map<Integer, Employee> employeeMap = new HashMap<>();
		for (Employee employee : employees) {
			employeeMap.put(employee.id, employee);
		}

		int totalImportance = 0;
		seen.add(id);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(id);
		while (!queue.isEmpty()) {
			int nextId = queue.poll();
			Employee employee = employeeMap.get(nextId);
			totalImportance += employee.importance;
			for (int subId : employee.subordinates) {
				if (!seen.contains(subId)) {
					seen.add(subId);
					queue.add(subId);
				}
			}
		}

		return totalImportance;
	}
}
