package com.anand.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.model.Order;

public interface OrderRepo extends JpaRepository<Order,Long> {
	

}
