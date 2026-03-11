package com.lpu.spring_assignment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.data.domain.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PostAuthorize;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.lpu.spring_assignment.entity.Student;
import com.lpu.spring_assignment.repo.StudentRepository;

@Service
public class StudentService {

	    @Autowired
	    private StudentRepository repo;


	    //@PreAuthorize("hasRole('ADMIN')")
	    public Student createStudent(Student student) {
	        return repo.save(student);
	    }


	    @Cacheable
	  
	    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	    public Student getStudent(Long id) {
	        return repo.findById(id).orElseThrow();
	    }


	    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	    public Page<Student> getAllStudents(int page,int size,String sort){

	        Pageable pageable = PageRequest.of(page,size, Sort.by(sort));

	        return repo.findAll(pageable);
	    }

	 
	    @PreAuthorize("hasRole('ADMIN')")
	    public Student updateStudent(Long id, Student student){

	        Student s = repo.findById(id).orElseThrow();

	        s.setEmail(student.getEmail());
	        s.setCourse(student.getCourse());
	        s.setMarks(student.getMarks());

	        return repo.save(s);
	    }

	
	    @PreAuthorize("hasRole('ADMIN')")
	    public void deleteStudent(Long id){
	        repo.deleteById(id);
	    }

	
	    public void uploadProfileImage(Long id, MultipartFile file){

	        try{
	            Student s = repo.findById(id).orElseThrow();
	            s.setProfileImage(file.getBytes());
	            repo.save(s);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }

	 
	    public void uploadAssignment(Long id, MultipartFile file){

	        try{
	            Student s = repo.findById(id).orElseThrow();
	            s.setAssignmentFile(file.getBytes());
	            repo.save(s);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }

	 
	    public byte[] downloadProfileImage(Long id){

	        Student s = repo.findById(id).orElseThrow(()->new RuntimeException("File Not found with "+id));
	        return s.getProfileImage();
	    }

	  
	    public byte[] downloadAssignment(Long id){

	        Student s = repo.findById(id).orElseThrow();
	        return s.getAssignmentFile();
	    }
	

}