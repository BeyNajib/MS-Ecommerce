package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/products")
    public List<Product> list()
    {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @GetMapping(value = "/products/{id}")
    public Optional<Product> get(@PathVariable Long id)
    {
        Optional<Product> productInstance = productRepository.findById(id);
        if (!productInstance.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified product doesn't exist");
        return productInstance;
    }

    @PostMapping(value = "/products")
    public String post(@RequestBody Product product){
        Product produitFinal = productRepository.saveAndFlush(product);
        if(produitFinal.getId() != null) {
            return "Produit crée";
        } else {
            return "Erreur lors de la création du produit (error 500)";
        }
    }
}
