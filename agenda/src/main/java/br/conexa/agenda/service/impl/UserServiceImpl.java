package br.conexa.agenda.service.impl;

import br.conexa.agenda.model.User;
import br.conexa.agenda.repository.UserRepository;
import br.conexa.agenda.service.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void create(User user) {
        Optional<User> existUser = this.userRepository.findByUserName(user.getUsername());
        if(existUser.isPresent()) {
            throw new EntityExistsException("Usuário já existe");
        }
        //faz o encrypt da senha
        user.setPassword(passwordEncoder().encode(user.getUsername()));
        this.userRepository.save(user);
    }
}
