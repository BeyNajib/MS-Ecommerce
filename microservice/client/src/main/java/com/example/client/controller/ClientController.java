package com.example.client.controller;

import com.example.client.bean.CartBean;
import com.example.client.bean.CartItemBean;
import com.example.client.bean.ProductBean;
import com.example.client.proxies.MsCartProxy;
import com.example.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String cartAdd(@RequestBody ProductBean productBean){
        CartItemBean cartItemBean =new CartItemBean(Long.valueOf(uniqueClientId), productBean.getId(), 1);
        if(!msCartProxy.getCart(Long.valueOf(uniqueClientId)).isPresent()) {
            msCartProxy.createNewCart(new CartBean(Long.valueOf(uniqueClientId), new ArrayList<CartItemBean>(){{add(cartItemBean);}}));
        }
        msCartProxy.addProductToCart(Long.valueOf(uniqueClientId), cartItemBean);
        return "addToCart";
    }
}
