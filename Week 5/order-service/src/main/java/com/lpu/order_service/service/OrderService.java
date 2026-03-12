package com.lpu.order_service.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.order_service.dto.Book;
import com.lpu.order_service.dto.BookDto;
import com.lpu.order_service.entity.Order;
import com.lpu.order_service.feign.BookFeignClientService;
import com.lpu.order_service.repo.OrderRepository;

@Service
public class OrderService {
	private final ConversionClass con;
    private final OrderRepository repo;
    private final BookFeignClientService bookClient;

    public OrderService(OrderRepository repo,BookFeignClientService bookClient,ConversionClass con) {
        this.repo = repo;
        this.bookClient= bookClient;
        this.con = con;
    }
    
    
    public Order createOrder(Order order) {

        Book book = bookClient.getBookById(order.getBookId());
        
        BookDto dto = con.convertFromBooktoDto(book);

        order.setTotalPrice(dto.getPrice() * order.getQuantity());
        order.setOrderDate(LocalDate.now());
        order.setStatus("PLACED");

        return repo.save(order);
    }

    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    public Order getOrderById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Order updateOrderStatus(Long id, String status) {

        Order order = repo.findById(id).orElseThrow();

        order.setStatus(status);

        return repo.save(order);
    }


   
   
    

    public Order deleteOrder(Long id) {

        Order order = repo.findById(id).orElseThrow();
        order.setStatus("CANCELLED");

        return repo.save(order);
    }
    
    
     
}