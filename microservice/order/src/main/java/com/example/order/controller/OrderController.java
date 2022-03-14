package com.example.order.controller;

import com.example.order.entity.CartBean;
import com.example.order.entity.CartItemBean;
import com.example.order.entity.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/order")
    public String createOrder(@RequestBody CartBean cartBean) {
        Order order = new Order();
        order.setCartBean(cartBean);
        order.setPaidTotal(0);
        double prix = 0;
        for(CartItemBean cartItemBean: cartBean.getProducts()) {
            prix += 1;
        }
        order.setTotalPrice(prix);
        return "created";
    }
}
