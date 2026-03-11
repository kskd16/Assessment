package com.lpu.employeeApp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.employeeApp.dto.EmployeeDto;
import com.lpu.employeeApp.entity.Company;
import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.exception.CompanyNotFoundException;
import com.lpu.employeeApp.exception.EmployeeNotFoundException;
import com.lpu.employeeApp.repository.CompanyRepository;
import com.lpu.employeeApp.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private CompanyRepository comRepo;

    public EmployeeDto saveEmployee(EmployeeDto dto) {
        Employee emp =  repo.save(DtoToEntity(dto));
        return entityToDto(emp);
    }

    public Employee updateEmployee(int id, Employee emp) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));

        existing.setName(emp.getName());
        existing.setAge(emp.getAge());
        existing.setPhone(emp.getPhone());

        return repo.save(existing);
    }

    public Employee findEmployeeById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
    }

    public List<Employee> findAll() {
        return repo.findAll();
    }

    public void deleteEmployeeById(int id) {
        Employee emp = findEmployeeById(id);
        repo.delete(emp);
    }

    public Employee assignEmployeeToCompany(int empId, int compId) {
        Employee emp = findEmployeeById(empId);

        Company comp = comRepo.findById(compId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + compId + " not found"));

        emp.setCompany(comp);
        return repo.save(emp);
    }
    
    public EmployeeDto entityToDto(Employee emp) {
    	EmployeeDto dto = new EmployeeDto();
    	dto.setId(emp.getId());
    	dto.setName(emp.getName());
    	dto.setPhone(emp.getPhone());
    	dto.setAge(emp.getAge());
    	
    	return dto;
    }
    public Employee DtoToEntity(EmployeeDto dto) {
    	Employee emp = new Employee();
    	emp.setId(dto.getId());
    	emp.setName(dto.getName());
    	emp.setPhone(dto.getPhone());
    	emp.setAge(dto.getAge());
    	
    	return emp;
    }
    
    
}