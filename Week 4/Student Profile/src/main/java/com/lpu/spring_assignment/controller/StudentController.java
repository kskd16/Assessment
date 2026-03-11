package com.lpu.spring_assignment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lpu.spring_assignment.entity.Student;
import com.lpu.spring_assignment.service.StudentService;

@RequestMapping("/student")
@RestController
public class StudentController {
	

	    @Autowired
	    private StudentService service;

	    @PostMapping("/create")
	    public Student create(@RequestBody Student student){
	        return service.createStudent(student);
	    }

	    @GetMapping("/get/{id}")
	    public Student getStudent(@PathVariable Long id){
	        return service.getStudent(id);
	    }

	    @GetMapping("/getAll")
	    public Page<Student> getAllStudents(@RequestParam int page,@RequestParam int size,@RequestParam String sort){

	        return service.getAllStudents(page,size,sort);
	    }

	    @PutMapping("/update/{id}")
	    public Student update(@PathVariable Long id,@RequestBody Student student){
	        return service.updateStudent(id,student);
	    }

	    @DeleteMapping("/delete/{id}")
	    public void delete(@PathVariable Long id){
	        service.deleteStudent(id);
	    }
	    @PostMapping("/uploadAssignment/{id}")
		public String uploadAssignment(@PathVariable Long id ,@RequestParam("file") MultipartFile file) throws IOException{
			
			
			service.uploadAssignment(id, file);
			return "Assignment saved with id: "+ id;
		}
	    
	    
	    @PostMapping("/uploadProfileImage/{id}")
		public String uploadProfileImage(@PathVariable Long id ,@RequestParam("file") MultipartFile file) throws IOException{
			
			
			service.uploadProfileImage(id, file);
			return "Profile Image saved with id: "+ id;
		}
	    
	    @GetMapping("/downloadAssignment/{id}")
		public byte[] downloadAssignment(@PathVariable Long id){
			return service.downloadAssignment(id);
			//return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + .getFileName()).header(HttpHeaders.CONTENT_TYPE,f.getFieldType()).body(f.getData());
			//return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment**;filename=\"" + f.getFileName()).header(HttpHeaders.CONTENT_TYPE,f.getFieldType()).body(f.getData());	
		}
		
		@GetMapping("/downloadProfileImage/{id}")
		public byte[] downloadProfileImage(@PathVariable Long id){
			return service.downloadProfileImage(id);
			//return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + .getFileName()).header(HttpHeaders.CONTENT_TYPE,f.getFieldType()).body(f.getData());
			//return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment**;filename=\"" + f.getFileName()).header(HttpHeaders.CONTENT_TYPE,f.getFieldType()).body(f.getData());	
		}
	}


