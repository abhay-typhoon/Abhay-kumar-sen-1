package com.LetsSee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Department {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 
	 private String name;
	 
	 private String hod;
	    
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
	public String getHod() {
		return hod;
	}
	public void setHod(String hod) {
		this.hod = hod;
	}
		

	public Department() {
	        // Default constructor
	    
	}
	
	public Department(int id, String name, String hod) {
			
		super();
			this.id = id;
			this.name = name;
			this.hod = hod;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", hod=" + hod + "]";
	}
	
	
	    
	    
	    
	    

}
