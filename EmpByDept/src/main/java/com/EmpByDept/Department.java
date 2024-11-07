package com.EmpByDept;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private int id;
	
	private String name;
	
	private String headOfDepartment;

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

	public String getHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(String headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	public Department(int id, String name, String headOfDepartment) {
		super();
		this.id = id;
		this.name = name;
		this.headOfDepartment = headOfDepartment;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", headOfDepartment=" + headOfDepartment + "]";
	}
	
	
	
}
