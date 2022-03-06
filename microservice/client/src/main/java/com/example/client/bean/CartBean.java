package com.example.client.bean;

import java.util.List;

public class CartBean {
    public CartBean(Long id, List<CartItemBean> products) {
        this.id = id;
        this.products = products;
    }

    private Long id;
    private List<CartItemBean> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemBean> getProducts() {
        return products;
    }

    public void setProducts(List<CartItemBean> products) {
        this.products = products;
    }
}
