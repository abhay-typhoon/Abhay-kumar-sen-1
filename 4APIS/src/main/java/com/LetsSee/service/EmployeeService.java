package com.LetsSee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LetsSee.Department;
import com.LetsSee.Employee;
import com.LetsSee.dao.DepartmentRepository;
import com.LetsSee.dao.EmployeeRepository;


@Service
public class EmployeeService {
	
	 @Autowired
	    private EmployeeRepository employeeRepository;
	 @Autowired
	    private DepartmentRepository deptRepo;
	 
	 

	public Optional<Employee> getEmployeeById(Integer empId) {
	        
		return employeeRepository.findById(empId);
	    
	}

	public List<Employee> getAllEmployees() {
		 
	     return  (List<Employee>) this.employeeRepository.findAll();	
		
		
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
	
	
	
	public List<Employee> getEmployeesByDepartment(String deptName) {
	    
		List<Department> deptList = deptRepo.findAll();
		Optional<Department> dept = deptList.stream().filter(i->deptName.equals(i.getName())).findAny();		
		List<Employee> empList = getAllEmployees();
		List<Employee> empListByDeptName = new ArrayList<>();
		
		
		if(!dept.isEmpty()) {
			
			
			for(Employee emp:empList) {
				
				if(emp.getId() == dept.get().getId()) {
					
					 empListByDeptName.add(emp);
					
				}
				
			}
			
		}
		
	
		
		return  empListByDeptName;
		//return employeeRepository.findByName(departmentName);
	}

	public Employee updateEmployee(Integer empId, Employee employeeDetails) {
		 Employee employee = getEmployeeById(empId).orElseThrow();
	        employee.setName(employeeDetails.getName());
	        employee.setSalary(employeeDetails.getSalary());
	        employee.setAddress(employeeDetails.getAddress());
	        employee.setDepartment(employeeDetails.getDepartment());
	        return saveEmployee(employee);
	}
	
	

	
	
	
	
}	

