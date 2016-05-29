package flipkart;

import java.util.LinkedList;
import java.util.List;

public class EmployeeWithReportees {
	
	private static int MAX_RATING = 5;
	
	private Employee employee;
	
	private List<List<EmployeeWithReportees>> reportees;
	
	int totalReportees;
	
	public EmployeeWithReportees (Employee employee) {
		this.employee = employee;
		reportees = new LinkedList<List<EmployeeWithReportees>>();
		for (int i=0; i<=MAX_RATING; i++) {
			reportees.add(new LinkedList<EmployeeWithReportees>());
		}
		totalReportees = 0;
	}
	
	public void addReportee(EmployeeWithReportees employeeWithReoprtees) {
		int reporteeRating = employeeWithReoprtees.getEmployee().getRating();
		
		List<EmployeeWithReportees> reporteesList = reportees.get(reporteeRating);
		if (reporteesList == null) {
			reporteesList = new LinkedList<EmployeeWithReportees>();
			reportees.add(reporteeRating, reporteesList);
		}
		reporteesList.add(employeeWithReoprtees);
		totalReportees++;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	private List<EmployeeWithReportees> getAllReporteesFlattened() {
		int size = reportees.size();
		List<EmployeeWithReportees> allReortees = new LinkedList<EmployeeWithReportees>();
		for (int i=size-1; i>=0; i--) {
			allReortees.addAll(reportees.get(i));
		}
		return allReortees;
	}
	
	public List<Employee> getTopKPercentEmployees(int percentage) {
		List<Employee> topEmployees =  this.getTopKPercentEmployeesCurrentLevel(percentage);
		List<EmployeeWithReportees> allReoprtees = getAllReporteesFlattened();
		while (allReoprtees.size() > 0) {
			EmployeeWithReportees employeeWithReoprtees = allReoprtees.remove(0);
			topEmployees.addAll(employeeWithReoprtees.getTopKPercentEmployeesCurrentLevel(percentage));
			allReoprtees.addAll(employeeWithReoprtees.getAllReporteesFlattened());
		}
		return topEmployees;
	}
	
	private List<Employee> getTopKPercentEmployeesCurrentLevel(int percentage) {
		List<Employee> topKPercentEmployees = new LinkedList<Employee>();
		int toReturn = (int) Math.ceil(totalReportees * percentage * 1.0 / 100);
		int counter = 0;
		int size = reportees.size();
		
		//Get current employees direct reportees
		for (int i=size-1; i>=0; i--) {
			List<EmployeeWithReportees> reporteesList = reportees.get(i);
			for (EmployeeWithReportees employeeWithReportees : reporteesList) {
				if (counter < toReturn) {
					topKPercentEmployees.add(employeeWithReportees.getEmployee());
					counter++;
					continue;
				}
				break;
			}
			if (counter >=  toReturn) {
				break;
			}
		}
		return topKPercentEmployees;
	}
}
