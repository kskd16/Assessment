package com.lpu.order_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.order_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}