package com.example.order.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class CartBean {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<CartItemBean> products;

    public CartBean(List<CartItemBean> products) {
        this.products = products;
    }

    public CartBean(){

    }

    public List<CartItemBean> getProducts() {
        return products;
    }

    public void setProducts(List<CartItemBean> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
