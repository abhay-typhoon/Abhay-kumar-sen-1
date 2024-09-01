package com.LetsSee.contoler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LetsSee.Employee;
import com.LetsSee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	 @Autowired
	    private EmployeeService employeeService;
	 

	    @GetMapping
	    public Iterable<Employee> getAllEmployees() {
	        return employeeService.getAllEmployees();
	    }
	    
	    @PostMapping
	    public Employee createEmployee(@RequestBody Employee employee) {
	        return employeeService.saveEmployee(employee);
	    }
	    
	    @DeleteMapping("/{empId}")
	    public void deleteEmployee(Integer empId) throws Exception {
	        employeeService.deleteEmployee(empId);
	    }
	    
	    @PutMapping("/{empId}")
	    public Employee updateEmployee(@PathVariable Integer empId, @RequestBody Employee employeeDetails) {
	        Employee employee = employeeService.getEmployeeById(empId).orElseThrow();
	        employee.setName(employeeDetails.getName());
	        employee.setSalary(employeeDetails.getSalary());
	        employee.setAddress(employeeDetails.getAddress());
	        employee.setDepartment(employeeDetails.getDepartment());
	        return employeeService.saveEmployee(employee);
	    }
	    
	    @GetMapping("/department/{deptName}")
	    public List<Employee> getEmployeesByDepartment(@PathVariable String deptName) {
	        return employeeService.getEmployeesByDepartment(deptName);
	    }

	    @GetMapping("/{empId}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable int empId) {
	        Optional<Employee> employee = employeeService.getEmployeeById(empId);
	        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    
	    

    	
    }
    
    
	
	

