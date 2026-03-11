package com.lpu.spring_assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.spring_assignment.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	
	Student findByEmail(String email);
}
