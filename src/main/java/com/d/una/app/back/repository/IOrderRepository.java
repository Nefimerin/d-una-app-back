package com.d.una.app.back.repository;

import com.d.una.app.back.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository  extends JpaRepository<Order, Long> {
}
