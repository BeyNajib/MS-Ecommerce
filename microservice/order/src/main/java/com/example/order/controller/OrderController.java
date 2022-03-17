package com.example.order.controller;

import com.example.order.entity.CartBean;
import com.example.order.entity.CartItemBean;
import com.example.order.entity.Commande;
import com.example.order.repository.CartBeanRepository;
import com.example.order.repository.CartItemBeanRepository;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    CartBeanRepository cartRepository;

    @Autowired
    CartItemBeanRepository cartItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/order")
    public List<Commande> getCommandes() {
        return this.orderRepository.findAll();
    }

    @GetMapping("/order/{id}")
    public Optional<Commande> getCommande(@PathVariable Long id){
        Optional<Commande> commande = orderRepository.findById(id);
        if (!commande.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified product doesn't exist");
        return commande;
    }

    @PostMapping("/order")
    public Optional<Commande> createOrder(@RequestBody CartBean cartBean) {
        Commande order = new Commande();
        double prix = 0;
        cartBean.setId(null);
        order.setId(null);
        order.setCartBean(cartBean);
        for(CartItemBean cartItemBean: cartBean.getProducts()) {
            cartItemBean.setId(null);
            prix += cartItemBean.getPrice() * cartItemBean.getQuantity();
        }
        order.setTotalPrice(prix);
        try {
            this.cartItemRepository.saveAll(cartBean.getProducts());
            this.cartRepository.save(cartBean);
            this.orderRepository.save(order);
        } catch (DataAccessException e) {
            System.out.println(e.getLocalizedMessage());
            return Optional.empty();
        }
        return Optional.of(order);
    }
}
