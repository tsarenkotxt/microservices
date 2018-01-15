package com.griddynamics.api.service.impl;

import com.griddynamics.api.domain.OrderInfo;
import com.griddynamics.api.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final RestTemplate restTemplate;

    private final String url;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate,
                            @Value("${service.protocol.order}") String protocol,
                            @Value("${service.domain.order}") String domain,
                            @Value("${service.port.order}") String port,
                            @Value("${service.path.order}") String path) {
        this.restTemplate = restTemplate;
        this.url = protocol + domain + ":" + port + path;
    }

    @Override
    public OrderInfo save(OrderInfo orderInfo) {
        LOGGER.info("Save orderInfo: {}", orderInfo);
        ResponseEntity<OrderInfo> response = restTemplate.postForEntity(url, orderInfo, OrderInfo.class);
        return response.getBody();
    }

    @Override
    public OrderInfo update(UUID id, OrderInfo updated) {
        LOGGER.info("Update orderInfo, id: {}, {}", id, updated);
        HttpEntity<OrderInfo> request = new HttpEntity<>(updated);
        ResponseEntity<OrderInfo> response = restTemplate.exchange(url + id, HttpMethod.PUT, request, OrderInfo.class);
        return response.getBody();
    }

    @Override
    public OrderInfo findOne(UUID id) {
        LOGGER.info("Find orderInfo, id: {}", id);
        ResponseEntity<OrderInfo> response = restTemplate.getForEntity(url + id, OrderInfo.class);
        return response.getBody();
    }

    @Override
    public List<OrderInfo> findAll() {
        LOGGER.info("Find all orders");
        ResponseEntity<OrderInfo[]> response = restTemplate.getForEntity(url, OrderInfo[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public void delete(UUID id) {
        LOGGER.info("Delete orderInfo, id: {}", id);
        restTemplate.delete(url + id);
    }

}
