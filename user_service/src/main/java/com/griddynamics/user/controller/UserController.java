package com.griddynamics.user.controller;

import com.griddynamics.user.domain.User;
import com.griddynamics.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getOne(@PathVariable UUID id) {
        return userService.findOne(id);
    }

    @PostMapping(value = "/")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping(value = "/{id}")
    public User update(@PathVariable UUID id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

}
