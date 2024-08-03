package javajdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db"; 
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "root"; 

    // Method to save an employee with a photo
    public boolean saveEmployee(Employee employee, String photoPath) {
        String query = "INSERT INTO employees (id, ename, dept, city, designation, doj, dob, photo, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int affectedRows = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             FileInputStream fis = new FileInputStream(new File(photoPath))) {

            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getEname());
            pstmt.setString(3, employee.getDept());
            pstmt.setString(4, employee.getCity());
            pstmt.setString(5, employee.getDesignation());
            pstmt.setDate(6, employee.getDoj());
            pstmt.setDate(7, employee.getDob());
            pstmt.setBinaryStream(8, fis, (int) new File(photoPath).length());
            pstmt.setDouble(9, employee.getSalary());

            affectedRows = pstmt.executeUpdate();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return affectedRows > 0;
    }

    // Method to retrieve all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getInt("id"),
                    rs.getString("ename"),
                    rs.getString("dept"),
                    rs.getString("city"),
                    rs.getString("designation"),
                    rs.getDate("doj"),
                    rs.getDate("dob"),
                    rs.getBytes("photo"),
                    rs.getDouble("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
    //Method to retrieve employees have experience more than 2 years
 // Method to retrieve employees having experience more than 'n' years
    public List<Employee> getEmployeesByExp(int n) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees WHERE TIMESTAMPDIFF(YEAR, doj, CURDATE()) >= ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, n);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("ename"),
                        rs.getString("dept"),
                        rs.getString("city"),
                        rs.getString("designation"),
                        rs.getDate("doj"),
                        rs.getDate("dob"),
                        rs.getBytes("photo"),
                        rs.getDouble("salary")
                    );
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }


    // Method to get an employee by ID
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        String query = "SELECT * FROM employees WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("ename"),
                        rs.getString("dept"),
                        rs.getString("city"),
                        rs.getString("designation"),
                        rs.getDate("doj"),
                        rs.getDate("dob"),
                        rs.getBytes("photo"),
                        rs.getDouble("salary")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    // Method to update employee salary
    public boolean updateEmployeeSalary(int id, double newSalary) {
        String query = "UPDATE employees SET salary = ? WHERE id = ?";
        int affectedRows = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, id);
            affectedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedRows > 0;
    }

    // Method to delete an employee by ID
    public boolean deleteEmployeeById(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        int affectedRows = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            affectedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedRows > 0;
    }

    // Method to retrieve the photo of an employee by ID
    public byte[] getEmployeePhotoById(int id) {
        byte[] photo = null;
        String query = "SELECT photo FROM employees WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    photo = rs.getBytes("photo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return photo;
    }

    // Utility method to save a byte array as an image file
    public void savePhotoToFile(byte[] photo, String outputPath) {
        if (photo != null) {
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                fos.write(photo);
                System.out.println("Photo retrieved and saved as '" + outputPath + "'.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No photo data found.");
        }
    }
}
