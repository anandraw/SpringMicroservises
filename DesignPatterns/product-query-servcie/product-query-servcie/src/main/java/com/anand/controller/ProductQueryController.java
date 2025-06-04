package com.anand.controller;

import com.anand.entity.Product;
import com.anand.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    private ProductQueryService queryService;

    @GetMapping
    public List<Product> getAll(){
        return queryService.getProducts();
    }


}
