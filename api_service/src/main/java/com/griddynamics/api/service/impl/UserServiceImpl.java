package com.griddynamics.api.service.impl;

import com.griddynamics.api.domain.User;
import com.griddynamics.api.service.UserService;
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
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final RestTemplate restTemplate;

    private final String url;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate,
                           @Value("${service.protocol.user}") String protocol,
                           @Value("${service.domain.user}") String domain,
                           @Value("${service.port.user}") String port,
                           @Value("${service.path.user}") String path) {
        this.restTemplate = restTemplate;
        this.url = protocol + domain + ":" + port + path;
    }

    @Override
    public User save(User user) {
        LOGGER.info("Save user: {}", user);
        ResponseEntity<User> response = restTemplate.postForEntity(url, user, User.class);
        return response.getBody();
    }

    @Override
    public User update(UUID id, User updated) {
        LOGGER.info("Update user, id: {}, {}", id, updated);
        HttpEntity<User> request = new HttpEntity<>(updated);
        ResponseEntity<User> response = restTemplate.exchange(url + id, HttpMethod.PUT, request, User.class);
        return response.getBody();
    }

    @Override
    public User findOne(UUID id) {
        LOGGER.info("Find user, id: {}", id);
        ResponseEntity<User> response = restTemplate.getForEntity(url + id, User.class);
        return response.getBody();
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Find all users");
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public void delete(UUID id) {
        LOGGER.info("Delete user, id: {}", id);
        restTemplate.delete(url + id);
    }

}
