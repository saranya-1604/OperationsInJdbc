package javajdbc;

import java.sql.Date;

//import java.sql.Date;

public class Employee {
    private int id;
    private String ename;
    private String dept;
    private String city;
    private String designation;
    private Date doj; // Date of Joining
    private Date dob; // Date of Birth
    private byte[] photo;
    private double salary;
	public Employee(int id, String ename, String dept, String city, String designation, Date doj, Date dob,
			byte[] photo, double salary) {
		super();
		this.id = id;
		this.ename = ename;
		this.dept = dept;
		this.city = city;
		this.designation = designation;
		this.doj = doj;
		this.dob = dob;
		this.photo = photo;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Employee() {
		super();
	}

    
}
