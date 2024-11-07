package com.EmpByDept.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmpByDept.EmpByDept;

public interface EmpByDeptRepository extends JpaRepository<EmpByDept, Integer> {

	List<EmpByDept> findByDepartmentId(int departmentId);
	
}
