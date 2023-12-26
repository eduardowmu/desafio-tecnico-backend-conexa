package br.conexa.agenda.service.impl;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.enumeration.UserRole;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;
import br.conexa.agenda.repository.UserRepository;
import br.conexa.agenda.service.UserService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends Facade implements UserService {
    private final UserRepository userRepository;

    @Override
    public void create(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        this.execute(data, "save");
        if(this.userRepository.findByUserName(data.email()).isPresent())
            throw new EntityExistsException("Usuário já existe " + data.email());

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        User newUser = new User(null, data.email(), encryptedPassword, data.especialidade() != null &&
                !data.especialidade().isEmpty() ? UserRole.ADMIN : UserRole.USER);

        this.userRepository.save(newUser);
    }
}
