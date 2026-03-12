package com.lpu.order_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lpu.order_service.dto.Book;
import com.lpu.order_service.dto.BookDto;

@FeignClient("BOOK-SERVICE")
public interface BookFeignClientService {

	
	 @PostMapping("/books/create")
	    public Book createBook(@RequestBody Book book);
	     

	    @GetMapping("/books/all")
	    public List<Book> getAllBooks() ;
	        

	    @GetMapping("/books/get/{id}")
	    public Book getBookById(@PathVariable Long id);
	        

	    @PutMapping("/books/update/{id}")
	    public Book updateBook(@PathVariable Long id,
	                           @RequestBody Book book);
	    

	    @DeleteMapping("/books/delete/{id}")
	    public void deleteBook(@PathVariable Long id);
	      
}
