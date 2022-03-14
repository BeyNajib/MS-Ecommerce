package com.example.order.repository;

import com.example.order.entity.CartItemBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemBeanRepository extends JpaRepository<CartItemBean, Long> {
}
