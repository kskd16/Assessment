package com.lpu.spring_assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
	

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;   
	    private String password;
	    
	    @Column(unique = true)
	    private String email;
	    private String course;
	    private int marks;

	    private String role;  

	    @Lob
	    private byte[] profileImage;

	    @Lob
	    private byte[] assignmentFile;

	  
	   

	

}
