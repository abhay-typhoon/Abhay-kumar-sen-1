package com.LetsSee.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.LetsSee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
	
	//@Query("select emp from Employee emp join Department dept on emp.department1 = dept.id where dept.name = :name")
	List<Employee> findByName( @Param("name")String name);
	
	
}
