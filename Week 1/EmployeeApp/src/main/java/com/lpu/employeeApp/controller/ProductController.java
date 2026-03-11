package com.lpu.employeeApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.employeeApp.entity.Product;
import com.lpu.employeeApp.service.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
	@PostMapping("/saveAll")
	public List<Product> saveAll(@RequestBody List<Product> list){
		return service.saveAllProduct(list);
	}
	
	@GetMapping("/page/{pageNumber}/{size}")
	public List<Product> productPagination(@PathVariable int pageNumber, @PathVariable int size){
		return service.productPagination(pageNumber, size);
		
	}
	
	@GetMapping("/sortDesc/{field}")
	public List<Product> sortProductInDesc(@PathVariable String field){
		return service.sortProductByFieldInDesc(field);
	}
	@GetMapping("/page/{pageNumber}/{size}/{field}")
	public List<Product> productPaginationSortedDesc(@PathVariable int pageNumber,@PathVariable int size, @PathVariable String field){
		return service.sortProductPage(pageNumber, size, field);
	}
	@GetMapping("/sortAsc/{field}")
	public List<Product> sortProductInAsc(@PathVariable String field){
		return service.sortProductByFieldInAsc(field);
	}

}
