package javajdbc;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Save a new employee with a photo
        Employee newEmployee = new Employee(7, "Sathya", "Finance", "Los Angeles", "Analyst", Date.valueOf("2020-09-10"), Date.valueOf("1995-11-22"), null, 70000);
        String photoPath = "D:/Downloads/virat.jpeg";
        boolean isSaved = employeeDAO.saveEmployee(newEmployee, photoPath);
        if (isSaved) {
            System.out.println("Employee with photo saved successfully.");
        } else {
            System.out.println("Failed to save employee with photo.");
        }

        Employee newEmployee2 = new Employee(8, "Rohan", "Marketing", "New York", "Manager", Date.valueOf("2022-05-15"), Date.valueOf("1988-02-20"), null, 750000.0);
        String photoPath2 = "D:/Downloads/images1.png";
        if (employeeDAO.saveEmployee(newEmployee2, photoPath2)) {
            System.out.println("Employee 2 saved successfully.");
        } else {
            System.out.println("Failed to save Employee 2.");
        }

        Employee newEmployee3 = new Employee(9, "Deepak", "IT", "San Francisco", "Developer", Date.valueOf("2021-03-22"), Date.valueOf("2003-11-4"), null, 98000.0);
        String photoPath3 = "D:/Downloads/images.jpeg";
        if (employeeDAO.saveEmployee(newEmployee3, photoPath3)) {
            System.out.println("Employee 3 saved successfully.");
        } else {
            System.out.println("Failed to save Employee 3.");
        }

        // Retrieve all employees
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        System.out.println("\nAll Employees:");
        for (Employee emp : allEmployees) {
            System.out.println(emp.getEname() + " - " + emp.getDept());
        }

     // Retrieve employees having 2 or more years of experience
        List<Employee> allEmployees1 = employeeDAO.getEmployeesByExp(2);
        System.out.println("\nEmployees having 2 or more years of experience:");
        for (Employee emp : allEmployees1) {
            System.out.println(emp.getEname() + " - " + emp.getDept());
        }


        // Get employee by ID
        Employee employee = employeeDAO.getEmployeeById(2);
        if (employee != null) {
            System.out.println("\nEmployee with ID 2: " + employee.getEname());
        } else {
            System.out.println("\nEmployee with ID 2 not found.");
        }

        // Update employee salary
        boolean isUpdated = employeeDAO.updateEmployeeSalary(2, 250000);
        if (isUpdated) {
            System.out.println("\nEmployee salary updated successfully.");
        } else {
            System.out.println("\nFailed to update employee salary.");
        }

        // Delete employee by ID (commented out)
        /*
        boolean isDeleted = employeeDAO.deleteEmployeeById(3);
        if (isDeleted) {
            System.out.println("\nEmployee with ID 3 deleted successfully.");
        } else {
            System.out.println("\nFailed to delete employee with ID 3.");
        }*/
    }
}
