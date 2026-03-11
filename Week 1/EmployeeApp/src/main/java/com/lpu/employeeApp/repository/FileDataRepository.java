package com.lpu.employeeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.employeeApp.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

}
