package br.conexa.agenda.service.impl;

import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.repository.UserRepository;
import br.conexa.agenda.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl extends Facade implements RegisterService {
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private final UserRepository userRepository;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void create(EntityModel em) {
//        Optional<User> existUser = this.userRepository.findByUserName(user.getUsername());
//        if(existUser.isPresent()) {
//            throw new EntityExistsException("Usuário já existe");
//        }
//        //faz o encrypt da senha
//        user.setPassword(passwordEncoder().encode(user.getUsername()));
//        return this.userRepository.save(user);
    }
}
