package flipkart;

import org.testng.annotations.Test;

public class EmplyeeDatabaseTest {
	@Test
	public void testEmployeeDatabase() {
	    EmployeeDatabase employeeDatabase =  new EmployeeDatabase();
	    employeeDatabase.addEmployee(1, "E1", 3, 100, -1);
	    
	    employeeDatabase.addEmployee(2, "E2", 3, 100, 1);
	    employeeDatabase.addEmployee(3, "E3", 4, 100, 1);
	    employeeDatabase.addEmployee(4, "E4", 5, 100, 1);
	    employeeDatabase.addEmployee(5, "E5", 4, 100, 1);
	    
	    employeeDatabase.addEmployee(6, "E6", 3, 100, 2);
	    employeeDatabase.addEmployee(7, "E7", 2, 100, 2);
	    employeeDatabase.addEmployee(8, "E8", 5, 100, 2);
	    
	    employeeDatabase.addEmployee(9,  "E9",  5, 100, 4);
	    employeeDatabase.addEmployee(10, "E10", 4, 100, 4);
	    
	    employeeDatabase.addEmployee(11, "E11", 5, 100, 9);
	    employeeDatabase.addEmployee(12, "E12", 5, 100, 9);
	    
	    System.out.println(employeeDatabase.getTopKPercentEmployees(1, 50));
	    System.out.println(employeeDatabase.getTopKPercentEmployees(6, 50));
	    System.out.println(employeeDatabase.getTopKPercentEmployees(4, 50));
	}
}
