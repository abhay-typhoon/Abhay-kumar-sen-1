package com.LetsSee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LetsSee.Employee;
import com.LetsSee.dao.EmployeeRepository;


@Service
public class EmployeeService {
	
	 @Autowired
	    private EmployeeRepository employeeRepository;
	 
	 
	 

	public Optional<Employee> getEmployeeById(Integer empId) {
	        
		return employeeRepository.findById(empId);
	    
	}

	public List<Employee> getAllEmployees() {
		 
		List<Employee> list =(List<Employee>) this.employeeRepository.findAll();	
		
		return list;
	}

	public void deleteEmployee(Integer empId) throws Exception {
	        
		if (!employeeRepository.existsById(empId)) {
	            throw new Exception("Employee with ID " + empId + " does not exist.");
	        
		}
	        employeeRepository.deleteById(empId);
	    
	}
	
	public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
	
	
	public List<Employee> getEmployeesByDepartment(String departmentName) {
	    return employeeRepository.findByDepartment1_Name(departmentName);
	}

				
}	

