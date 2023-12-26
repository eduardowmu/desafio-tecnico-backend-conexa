package br.conexa.agenda.controller;

import br.conexa.agenda.model.User;
import br.conexa.agenda.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final RegisterServiceImpl registerServiceService;
    @Autowired
    public UserController(RegisterServiceImpl registerServiceService) {
        this.registerServiceService = registerServiceService;
    }
    @PostMapping("/create")
    public void create(@RequestBody User user) {
        this.registerServiceService.create(user);
    }
}