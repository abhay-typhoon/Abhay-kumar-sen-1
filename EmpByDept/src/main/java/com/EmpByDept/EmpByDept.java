package com.EmpByDept;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class EmpByDept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn( name = "department_id")
	private Department department;
	
	@OneToMany(mappedBy = "department")
	private List<EmpByDept> employee;

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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<EmpByDept> getEmployee() {
		return employee;
	}

	public void setEmployee(List<EmpByDept> employee) {
		this.employee = employee;
	}

	public EmpByDept(int id, String name, Department department, List<EmpByDept> employee) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmpByDept [id=" + id + ", name=" + name + ", department=" + department + ", employee=" + employee + "]";
	}
	
	

}
