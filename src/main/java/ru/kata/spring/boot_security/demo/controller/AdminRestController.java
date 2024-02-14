package ru.kata.spring.boot_security.demo.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("rest/admin")
public class AdminRestController {
    private final UserService userService;
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User show(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping()
    public List<User> create(@RequestBody User user) {
        userService.saveNewUser(user, user.getRoles());
        return userService.getAllUsers();
    }

    @PutMapping()
    public User update(@RequestBody User user) {
        userService.updateUser(user, user.getRoles());
        return user;
    }

    @DeleteMapping("/{id}")
    public List<User> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return userService.getAllUsers();
    }
}
