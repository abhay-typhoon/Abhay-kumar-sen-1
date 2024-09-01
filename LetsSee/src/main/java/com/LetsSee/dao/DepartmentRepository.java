package com.LetsSee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LetsSee.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
