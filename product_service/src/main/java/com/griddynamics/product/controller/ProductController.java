package com.griddynamics.product.controller;

import com.griddynamics.product.domain.Product;
import com.griddynamics.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product getOne(@PathVariable UUID id) {
        return productService.findOne(id);
    }

    @PostMapping(value = "/")
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping(value = "/{id}")
    public Product update(@PathVariable UUID id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }

}
