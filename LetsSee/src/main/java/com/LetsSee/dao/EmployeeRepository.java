package com.LetsSee.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.LetsSee.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
	
	public  List<Employee> findByDepartment1_Name(String departmentName);



}
