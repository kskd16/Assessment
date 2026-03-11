package com.viom.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viom.assignment.entity.Employee;
import com.viom.assignment.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;
	
	
	public Employee saveEmployee(Employee e) {
		return repo.save(e);
	}
	
	public void deleteEmployee(Employee e) {
		repo.delete(e);
	
	}
	public Employee findEmployeeById(int id) {
		Optional<Employee> opt = repo.findById(id);
		return opt.get();
	}
	
	public List<Employee> findAll(){
		return repo.findAll();
	}
	
	public void deleteEmployeeById(int id) {
		repo.deleteById(id);
	}
	public List<Employee> saveAll(List<Employee> list){
		return repo.saveAll(list);
	}
	
	public List<Employee> findByName(String name){
		return repo.findByName(name);
	}
	public List<Employee> findByDepartment(String department){
		return repo.findByDepartment(department);
	}
	public List<Employee> findByPhone(long phone){
		return repo.findByPhone(phone);
	}
	public List<Employee> findByEmail(String email){
		return repo.findByEmail(email);
	}
	public List<Employee> getEmployeeInRange(){
		return repo.getSalarywithRange();
	}
	public List<Employee> getEmployeeMaxSal(){
		return repo.getEmployeeMaxSalary();
	}
	
	public List<Employee> getEmployeeMinSal(){
		return repo.getEmployeeMinSalary();
	}
	public List<Employee> getEmpInTest(){
		return repo.getEmployeeInTesting();
	}


}
