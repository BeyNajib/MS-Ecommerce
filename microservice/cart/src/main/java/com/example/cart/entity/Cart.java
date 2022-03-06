package com.example.cart.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    public Cart(Long id, List<CartItem> products) {
        this.id = id;
        this.products = products;
    }

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> products;

    public Cart() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }

    public void addProduct(CartItem cartItem) {
    }
}
