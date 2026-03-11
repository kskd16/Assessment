package com.viom.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viom.assignment.entity.Employee;
import com.viom.assignment.service.EmployeeService;


@RequestMapping("/api")
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService ser;
	
	
	@PostMapping("/employees/save")
	public Employee addEmployee(@RequestBody Employee e) {
		return ser.saveEmployee(e);
	}
	
	@GetMapping("/employees/fetchall")
	public List<Employee> fetchAllEmployees(){
		return ser.findAll();
	}
	
	@GetMapping("/employees/getbyid")
	public Employee getEmployeeById(@RequestParam int id) {
		return ser.findEmployeeById(id);
	}
	
	@DeleteMapping("/employees/delete")
	public String deleteById(@RequestParam int id) {
		ser.deleteEmployeeById(id);
		return id +" deleted.";
	}
	
	@GetMapping("/employees/search/name")
	public List<Employee> getByName(@RequestParam	String name){
		return ser.findByName(name);
	}
	@GetMapping("/employees/search/department")
	public List<Employee> getByDepatment(@RequestParam	String department){
		return ser.findByDepartment(department);
	}
	@GetMapping("/employees/search/phone")
	public List<Employee> getByPhone(@RequestParam long phone){
		return ser.findByPhone(phone);
	}
	@GetMapping("/employees/search/email")
	public List<Employee> getByEmail(@RequestParam	String email){
		return ser.findByEmail(email);
	}
	
	@PutMapping("/employees/update")
	public Employee updateDetails(@RequestParam int id, @RequestBody Employee e) {
		Employee emp = ser.findEmployeeById(id);
		if(e.getId() == emp.getId()) {
			ser.saveEmployee(e);
		}
		return e;
	}
	
	@GetMapping("/employees/inRange")
	public List<Employee> inRange(){
		return ser.getEmployeeInRange();
	}
	
	@GetMapping("/employees/highestSal")
	public List<Employee> highestSal(){
		return ser.getEmployeeMaxSal();
	}
	
	@GetMapping("/employees/lowestSal")
	public List<Employee> lowestSal(){
		return ser.getEmployeeMinSal();
	}
	
	@GetMapping("/employees/empInTest")
	public List<Employee> empInTest(){
		return ser.getEmpInTest();
	}

	
	
	
	/*# Endpoint 
	1 /api/employees 
	2 /api/employees 
	3 /api/employees 
	4 /api/employees 
	HTTP 
	Method Description 
	POST 
	GET 
	GET 
	DELETE 
	5 /api/employees/search GET 
	6 /api/employees/search GET 
	Add a new 
	employee 
	Fetch all 
	employees 
	Get employee 
	by ID (using 
	query param) 
	Delete 
	employee by ID 
	(using query 
	param) 
	Get employees 
	by name 
	Get employees 
	Example Usage 
	Send Employee JSON in request body 
	/api/employees 
	/api/employees?id=10 
	/api/employees?id=10 
	/api/employees/search?name=Ravi 
	by department /api/employees/search?department=Testing 
	7 /api/employees/search GET 
	8 /api/employees/search GET 
	Get employee 
	by phone 
	Get employee 
	by email 
	/api/employees/search?phone=9876543210 
	/api/employees/search?email=ravi@gmail.com*/
}
