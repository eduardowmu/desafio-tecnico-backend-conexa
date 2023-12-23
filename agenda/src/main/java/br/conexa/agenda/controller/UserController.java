package br.conexa.agenda.controller;

import br.conexa.agenda.model.User;
import br.conexa.agenda.service.UserService;
import br.conexa.agenda.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return this.userService.create(user);
    }
}