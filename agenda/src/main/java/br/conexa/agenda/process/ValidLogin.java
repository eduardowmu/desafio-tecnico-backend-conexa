package br.conexa.agenda.process;

import br.conexa.agenda.dto.AuthenticationDto;
import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;
import br.conexa.agenda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidLogin implements ValidationProcess {
    private final UserRepository userRepository;
    @Autowired
    public ValidLogin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void process(EntityModel entityModel) {
        AuthenticationDto data = (AuthenticationDto)entityModel;
        User login = this.userRepository.findByUserName(data.userName())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não existe " + data.userName()));
    }
}