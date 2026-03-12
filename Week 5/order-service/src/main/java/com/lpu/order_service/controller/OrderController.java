package com.lpu.order_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.order_service.dto.Book;
import com.lpu.order_service.dto.BookDto;
import com.lpu.order_service.entity.Order;
import com.lpu.order_service.feign.BookFeignClientService;
import com.lpu.order_service.service.ConversionClass;
import com.lpu.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	//@Autowired
    private final OrderService service;
    private ConversionClass con;
    private BookFeignClientService bService;
	public OrderController(OrderService service, ConversionClass con, BookFeignClientService bService) {
        this.service = service;
        this.con = con;
        this.bService= bService;
    }
	
	
    
    //@Autowired
    

    
    
    @PostMapping("/bCreate")
    public BookDto createBook(@RequestBody Book b) {
    	Book book= bService.createBook(b);
    	return con.convertFromBooktoDto(book);
    }
    
    @GetMapping("/bAll")
    public List<BookDto> getAllBooks(){
         List<Book> books= bService.getAllBooks();
         List<BookDto> dtos= new ArrayList<BookDto>();
         for(Book b : books) {
        	 dtos.add(con.convertFromBooktoDto(b));
         }
         
         return dtos;
    }
    
    @GetMapping("/bGet/{id}")
    public BookDto getBookById(@PathVariable Long id) {
    	Book b =  bService.getBookById(id);
    	return con.convertFromBooktoDto(b);
    }
    
    @PutMapping("/bUpdate/{id}")
    public BookDto updateBook(@PathVariable Long id,
    		@RequestBody Book book) {
    	Book b =  bService.updateBook(id, book);
    	return con.convertFromBooktoDto(b);
    }
    
    @DeleteMapping("/bDelete/{id}")
    public void deleteBook(@PathVariable Long id){
    	 bService.deleteBook(id);
    	
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/get/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    @PutMapping("/update/{id}")
    public Order updateStatus(@PathVariable Long id,
                              @RequestParam String status) {
        return service.updateOrderStatus(id, status);
    }

    @PutMapping("/delete/{id}")
    public Order deleteOrder(@PathVariable Long id) {
       return service.deleteOrder(id);
    }
}
