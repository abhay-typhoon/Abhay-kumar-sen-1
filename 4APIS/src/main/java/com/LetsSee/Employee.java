package com.LetsSee;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity

public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	private String name;
    private double salary;
    private String address;
   
    
    @ManyToOne 
    @JoinColumn(name = "department1_id")
    
    private Department department1;
   
    
	public Employee(int id, String name, double salary, String address, Department department1) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.address = address;
		this.department1 = department1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public Department getDepartment() {
		return department1;
	}
	public void setDepartment(Department department) {
		this.department1 = department;
	}

	
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", address=" + address + ", department="
				+ department1 + "]";
	}
	
	
	
	   
    
    
	
}
