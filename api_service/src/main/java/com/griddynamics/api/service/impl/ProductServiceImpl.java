package com.griddynamics.api.service.impl;

import com.griddynamics.api.domain.Product;
import com.griddynamics.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final RestTemplate restTemplate;

    private final String url;

    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate,
                              @Value("${service.protocol.product}") String protocol,
                              @Value("${service.domain.product}") String domain,
                              @Value("${service.port.product}") String port,
                              @Value("${service.path.product}") String path) {
        this.restTemplate = restTemplate;
        this.url = protocol + domain + ":" + port + path;
    }

    @Override
    public Product save(Product product) {
        LOGGER.info("Save product: {}", product);
        ResponseEntity<Product> response = restTemplate.postForEntity(url, product, Product.class);
        return response.getBody();
    }

    @Override
    public Product update(UUID id, Product updated) {
        LOGGER.info("Update product, id: {}, {}", id, updated);
        HttpEntity<Product> request = new HttpEntity<>(updated);
        ResponseEntity<Product> response = restTemplate.exchange(url + id, HttpMethod.PUT, request, Product.class);
        return response.getBody();
    }

    @Override
    public Product findOne(UUID id) {
        LOGGER.info("Find product, id: {}", id);
        ResponseEntity<Product> response = restTemplate.getForEntity(url + id, Product.class);
        return response.getBody();
    }

    @Override
    public List<Product> findAll() {
        LOGGER.info("Find all products");
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public void delete(UUID id) {
        LOGGER.info("Delete product, id: {}", id);
        restTemplate.delete(url + id);
    }

}
