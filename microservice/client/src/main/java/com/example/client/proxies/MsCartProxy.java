package com.example.client.proxies;

import com.example.client.bean.CartBean;
import com.example.client.bean.CartItemBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "ms-cart", url = "localhost:8092")
public interface MsCartProxy {
    @PostMapping(value = "/cart")
    ResponseEntity<CartBean> createNewCart();

    @GetMapping(value = "/cart/{id}")
    Optional<CartBean> getCart(@PathVariable Long id);

    @PostMapping(value = "/cart/{id}")
    ResponseEntity<CartBean> addProductToCart(@PathVariable Long id, @RequestBody CartItemBean cartItem);

    @DeleteMapping(value = "/cart/{id}/empty")
    void emptyCart(@PathVariable Long id);
}
