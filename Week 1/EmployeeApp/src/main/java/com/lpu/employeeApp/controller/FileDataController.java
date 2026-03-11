package com.lpu.employeeApp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lpu.employeeApp.entity.FileData;
import com.lpu.employeeApp.repository.FileDataRepository;

@RestController
public class FileDataController {
	@Autowired
	private FileDataRepository repo;
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) throws IOException{
		FileData fd = new FileData(); 
		fd.setFileName(file.getOriginalFilename());
		fd.setFieldType(file.getContentType());
		fd.setData(file.getBytes());
		FileData saved = repo.save(fd);
		return "File saved with id: "+saved.getId();
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable Long id){
		FileData f = repo.findById(id)
				.orElseThrow(()-> new RuntimeException("File not found with "+id));
		//return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + f.getFileName()).header(HttpHeaders.CONTENT_TYPE,f.getFieldType()).body(f.getData());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment**;filename=\"" + f.getFileName()).header(HttpHeaders.CONTENT_TYPE,f.getFieldType()).body(f.getData());
		
		
	}
	
	

}
