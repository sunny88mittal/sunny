package flipkart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDatabase {

	private Map<Integer, EmployeeWithReportees> employeesMap;
	
	public EmployeeDatabase() {
		employeesMap = new HashMap<Integer, EmployeeWithReportees>();
	}
	
	public void addEmployee(int id, String name, int rating, int salary, int managerId) {
		Employee employee = new Employee(id, name, rating, salary);
		EmployeeWithReportees employeeWithReportees = new EmployeeWithReportees(employee);
		EmployeeWithReportees manager = employeesMap.get(managerId);
		if (manager != null) {
			manager.addReportee(employeeWithReportees);
		}
		employeesMap.put(id, employeeWithReportees);
	}
	
	public List<Employee> getTopKPercentEmployees(int employeeId, int percentage) {
		EmployeeWithReportees employeeWithReoprtees = employeesMap.get(employeeId);
		if (employeeWithReoprtees !=  null) {
			return employeeWithReoprtees.getTopKPercentEmployees(percentage);	
		}
		return new ArrayList<Employee>();
	}
}
