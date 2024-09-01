package com.LetsSee.contoler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LetsSee.Department;
import com.LetsSee.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	 @Autowired
	    private DepartmentService departmentService;

	    @PostMapping
	    public Department createDepartment(@RequestBody Department department) {
	        return departmentService.saveDepartment(department);
	    }

}
