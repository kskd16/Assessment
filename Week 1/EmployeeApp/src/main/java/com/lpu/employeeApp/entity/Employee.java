package com.lpu.employeeApp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
public class Employee {
	@Id
	 private int id ;
	@NotBlank(message = "name cannot be blank")
	 private String name; 
	
	@Positive(message = "phone number should be +ve")
	@Pattern(regexp = "\\d{10}")
	 private long phone; 
	 
	 
	 @Min(value = 18,message = "min is 18")
	 @Max(value = 65,message ="max is 65")
	 private int age;
	 
	 
	 @ManyToOne
	 @JoinColumn
	 private Company company;
	 
//	 private int salary; 
//	 private String department;
	 
	 
	 public Employee() {
		 
	 }


	 public Company getCompany() {
		return company;
	}


	 public void setCompany(Company company) {
		 this.company = company;
	 }


	 public int getAge() {
		 return age;
	 }


	 public int getId() {
		 return id;
	 }


	 public void setId(int id) {
		 this.id = id;
	 }


	 public String getName() {
		 return name;
	 }


	 public void setName(String name) {
		 this.name = name;
	 }


	 public long getPhone() {
		 return phone;
	 }


	 public void setPhone(long phone) {
		 this.phone = phone;
	 }





	 public void setAge(int age) {
		 this.age = age;
	 }



	 
}
