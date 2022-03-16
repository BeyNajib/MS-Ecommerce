package com.example.client.controller;

import com.example.client.bean.CartBean;
import com.example.client.bean.CartItemBean;
import com.example.client.bean.CommandeBean;
import com.example.client.bean.ProductBean;
import com.example.client.proxies.MsCartProxy;
import com.example.client.proxies.MsCommandeProxy;
import com.example.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;
    @Autowired
    private MsCartProxy msCartProxy;
    @Autowired
    private MsCommandeProxy msOrderProxy;

    private Long cartId;

    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Optional<ProductBean> product = msProductProxy.get(id);
        model.addAttribute("product", product);
        return "detail";
    }

    @GetMapping("/cart/add/{id}")
    public String afterAddToCart(@PathVariable Long id, Model model){
        Optional<CartBean> cartBean = msCartProxy.getCart(id);
        model.addAttribute("cart", cartBean);
        return "afterAddToCart";
    }

    @PostMapping(value = "/cart/add/{productId}", produces = "text/plain")
    public CartBean cartAdd(@PathVariable Long productId){
        Optional<ProductBean> productBean = msProductProxy.get(productId);
        if(!productBean.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get product" + productId);
        }
        CartItemBean cartItemBean = new CartItemBean(productId, 1, productBean.get().getPrice());
        if(this.cartId == null) {
            ResponseEntity<CartBean> cartBeanResponseEntity = msCartProxy.createNewCart();
            if(cartBeanResponseEntity.getBody() != null) {
                this.cartId = cartBeanResponseEntity.getBody() .getId();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't create cart properly");
            }
        }
        return msCartProxy.addProductToCart(this.cartId, cartItemBean).getBody();
    }

    @PostMapping("cart/order")
    public CommandeBean cartOrder(){
        Optional<CartBean> cartBean = msCartProxy.getCart(this.cartId);
        Optional<CommandeBean> orderBean;
        if(cartBean.isPresent())  {
            orderBean = msOrderProxy.createOrder(cartBean.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart" + this.cartId);
        }
        if(orderBean != null && orderBean.isPresent()) {
            this.msCartProxy.emptyCart(this.cartId);
            return orderBean.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't create order properly");
        }
    }
}
