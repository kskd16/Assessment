package com.lpu.employeeApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.employeeApp.dto.EmployeeDto;
import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.service.EmployeeService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> saveEmployee(@Valid @RequestBody EmployeeDto dto) {
        return new ResponseEntity(service.saveEmployee(dto), HttpStatus.CREATED);
    }

    @GetMapping("/getEmp/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        return ResponseEntity.ok(service.findEmployeeById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id,
                                                   @Valid @RequestBody Employee e) {
        return ResponseEntity.ok(service.updateEmployee(id, e));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping("emp/{empId}/assign/{compId}")
    public ResponseEntity<Employee> assignEmployee(@PathVariable int empId,
                                                   @PathVariable int compId) {
        return ResponseEntity.ok(service.assignEmployeeToCompany(empId, compId));
    }
}