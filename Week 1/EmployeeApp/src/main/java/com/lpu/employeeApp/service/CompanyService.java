package com.lpu.employeeApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.employeeApp.entity.Company;
import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.exception.CompanyNotFoundException;
import com.lpu.employeeApp.repository.CompanyRepository;
import com.lpu.employeeApp.repository.EmployeeRepository;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repo;

    @Autowired
    private EmployeeRepository empRepo;

    public Company saveCompany(Company c) {
        return repo.save(c);
    }

    public Company updateCompany(int id, Company company) {
        Company existing = repo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + id + " not found"));

        existing.setName(company.getName());
        existing.setLocation(company.getLocation());

        return repo.save(existing);
    }

    public Company findCompanyById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + id + " not found"));
    }

    public List<Company> findAll() {
        return repo.findAll();
    }

    public void deleteCompanyById(int id) {
        Company comp = findCompanyById(id);
        repo.delete(comp);
    }

    public Company saveCompanyWithEmployee(Company c) {
        c.getEmployees().forEach(emp -> emp.setCompany(c));
        return repo.save(c);
    }

    public Company addEmployeesToExistingCompany(int compId, List<Employee> employees) {
        Company company = findCompanyById(compId);

        employees.forEach(emp -> {
            emp.setCompany(company);
            empRepo.save(emp);
        });

        return company;
    }
}
