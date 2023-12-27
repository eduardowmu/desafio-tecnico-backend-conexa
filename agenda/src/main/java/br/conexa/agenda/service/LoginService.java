package br.conexa.agenda.service;

import br.conexa.agenda.dto.LoginResponseDto;
import br.conexa.agenda.model.EntityModel;
import org.springframework.security.authentication.AuthenticationManager;

@FunctionalInterface
public interface LoginService {
    void login(EntityModel em);
}
