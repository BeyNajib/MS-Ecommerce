package com.example.client.controller;

import com.example.client.bean.CartBean;
import com.example.client.bean.CartItemBean;
import com.example.client.bean.ProductBean;
import com.example.client.proxies.MsCartProxy;
import com.example.client.proxies.MsProductProxy;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;
    @Autowired
    private MsCartProxy msCartProxy;

    private String uniqueClientId = "111";

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

    @PostMapping(value = "/cart/add", produces = "text/plain")
    public String cartAdd(@ModelAttribute ProductBean productBean){
        CartItemBean cartItemBean = new CartItemBean(productBean.getId(), 1);
        ResponseEntity<CartBean> cartBeanResponseEntity = msCartProxy.createNewCart();
        CartBean cart = msCartProxy.addProductToCart(cartBeanResponseEntity.getBody().getId(), cartItemBean).getBody();
        return "ajouter au panier";
    }
}
