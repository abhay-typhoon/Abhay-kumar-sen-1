package com.LetsSee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LetsSee.Department;
import com.LetsSee.dao.DepartmentRepository;

@Service
public class DepartmentService {

	 @Autowired
	    private DepartmentRepository departmentRepository;

	    public Department saveDepartment(Department department) {
	        return departmentRepository.save(department);
	    }
	
}
