package com.lpu.employeeApp.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.employeeApp.entity.Company;
import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.service.CompanyService;
@RestController
@RequestMapping("/comp")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PostMapping("/save")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        return new ResponseEntity<>(service.saveCompany(company), HttpStatus.CREATED);
    }

    @PostMapping("/save/with-employees")
    public ResponseEntity<Company> saveCompanyWithEmployees(@RequestBody Company company) {
        return new ResponseEntity<>(service.saveCompanyWithEmployee(company), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        return ResponseEntity.ok(service.findCompanyById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id,
                                                 @RequestBody Company company) {
        return ResponseEntity.ok(service.updateCompany(id, company));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        service.deleteCompanyById(id);
        return ResponseEntity.ok("Company deleted successfully");
    }

    @PostMapping("/{id}/add-employees")
    public ResponseEntity<Company> addEmployees(@PathVariable int id,
                                                @RequestBody List<Employee> employees) {
        return ResponseEntity.ok(service.addEmployeesToExistingCompany(id, employees));
    }
}