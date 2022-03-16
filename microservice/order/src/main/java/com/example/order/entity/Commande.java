package com.example.order.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private Long id;

    private Timestamp timestamp;
    private double totalPrice;
    private double paidTotal;
    @OneToOne(cascade=CascadeType.ALL)
    private CartBean cartBean;

    public Commande() {
        this.paidTotal = 0;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Commande(Long id, CartBean cartBean, Timestamp timestamp, double totalPrice, double paidTotal) {
        this.id = id;
        this.cartBean = cartBean;
        this.timestamp = timestamp;
        this.totalPrice = totalPrice;
        this.paidTotal = paidTotal;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPaidTotal() {
        return paidTotal;
    }

    public void setPaidTotal(double paidTotal) {
        this.paidTotal = paidTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartBean getCartBean() {
        return cartBean;
    }

    public void setCartBean(CartBean cartBean) {
        this.cartBean = cartBean;
    }
}
