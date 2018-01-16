package com.griddynamics.api.service.impl;

import com.griddynamics.api.domain.Product;
import com.griddynamics.api.exception.RemoteServerException;
import com.griddynamics.api.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "saveFallback")
    @Override
    public Product save(Product product) {
        LOGGER.info("Save product: {}", product);
        ResponseEntity<Product> response = restTemplate.postForEntity(url, product, Product.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "updateFallback")
    @Override
    public Product update(UUID id, Product updated) {
        LOGGER.info("Update product, id: {}, {}", id, updated);
        HttpEntity<Product> request = new HttpEntity<>(updated);
        ResponseEntity<Product> response = restTemplate.exchange(url + id, HttpMethod.PUT, request, Product.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "findOneFallback")
    @Override
    public Product findOne(UUID id) {
        LOGGER.info("Find product, id: {}", id);
        ResponseEntity<Product> response = restTemplate.getForEntity(url + id, Product.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    @Override
    public List<Product> findAll() {
        LOGGER.info("Find all products");
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
        return Arrays.asList(response.getBody());
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    @Override
    public void delete(UUID id) {
        LOGGER.info("Delete product, id: {}", id);
        restTemplate.delete(url + id);
    }

    private Product saveFallback(Product product) {
        throw new RemoteServerException("Can't save product, 'Product Service' failure, " + product);
    }

    private Product updateFallback(UUID id, Product product) {
        throw new RemoteServerException(
                String.format("Can't update product, 'Product Service' failure, id: %s, %s", id, product));
    }

    private Product findOneFallback(UUID id) {
        throw new RemoteServerException("Can't find product, 'Product Service' failure, id: " + id);
    }

    private List<Product> findAllFallback() {
        throw new RemoteServerException("Can't find all products, 'Product Service' failure");
    }

    private void deleteFallback(UUID id) {
        throw new RemoteServerException("Can't delete product, 'Product Service' failure, id: " + id);

    }

}
