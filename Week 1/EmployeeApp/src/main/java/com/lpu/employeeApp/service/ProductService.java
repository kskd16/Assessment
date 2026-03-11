package com.lpu.employeeApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lpu.employeeApp.entity.Product;
import com.lpu.employeeApp.repository.ProductRepository;

@Service
public class ProductService {
	
	
	private ProductRepository repo;
	
	@Autowired
	public ProductService (ProductRepository repo) {
		super();
		this.repo = repo;
	}
	
	public List<Product> saveAllProduct(List<Product> list){
		return repo.saveAll(list);
	}
	
	public List<Product> productPagination(int pageNumber, int size){
		Pageable pageable = PageRequest.of(pageNumber, size);
		return repo.findAll(pageable).getContent();
	}
	public List<Product> sortProductPage(int pageNumber, int size,String field){
		Pageable pageable = PageRequest.of(pageNumber, size,Sort.by(field).descending());
		return repo.findAll(pageable).getContent();
	}
	
	public List<Product> sortProductByFieldInDesc(String field){
		return repo.findAll(Sort.by(field).descending());
	}
	
	public List<Product> sortProductByFieldInAsc(String field){
		return repo.findAll(Sort.by(field).ascending());
	}
	
	public List<Product> sortProductByNameInDesc(String name){
		return repo.findAll(Sort.by(name).descending());
	}

}
