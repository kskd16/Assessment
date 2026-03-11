package com.viom.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viom.assignment.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
	//@Query("select e from Employee e where e.name = :name")
	List<Employee> findByName(String name);
	
	//@Query("select e from Employee e where e.department = :department")
	List<Employee> findByDepartment(String department);
	
	//@Query("select e from Employee e where e.phone = :phone")
	List<Employee> findByPhone(long phone);
	
	//@Query("select e from Employee e where e.email = :email")
	List<Employee> findByEmail(String email);
	
	@Query("select e from Employee e where e.salary > 30000 and e.salary<60000")
	List<Employee> getSalarywithRange();
	
	@Query(nativeQuery= true, value = "select * from employee where salary IN (select max(salary) from employee )")
	List<Employee> getEmployeeMaxSalary();
	
	@Query(nativeQuery= true, value = "select * from employee where salary IN (select min(salary) from employee )")
	List<Employee> getEmployeeMinSalary();
	
	@Query(nativeQuery = true, value = "select * from employee where department = 'Testing'")
	List<Employee> getEmployeeInTesting();
}
