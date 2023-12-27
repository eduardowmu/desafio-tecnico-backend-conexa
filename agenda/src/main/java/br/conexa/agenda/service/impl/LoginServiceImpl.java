package br.conexa.agenda.service.impl;

import br.conexa.agenda.dto.AuthenticationDto;
import br.conexa.agenda.dto.LoginResponseDto;
import br.conexa.agenda.enumeration.Event;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;
import br.conexa.agenda.process.ValidLogin;
import br.conexa.agenda.process.ValidationProcess;
import br.conexa.agenda.repository.UserRepository;
import br.conexa.agenda.security.TokenService;
import br.conexa.agenda.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl extends Facade implements LoginService {
    private final UserRepository userRepository;
    @Autowired
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.roles = new HashMap<>();

        ValidLogin vl = new ValidLogin(this.userRepository);

        List<ValidationProcess> loginProcesses = new ArrayList<>();
        loginProcesses.add(vl);

        Map<Event, List<ValidationProcess>> orderRoles = new HashMap<>();
        orderRoles.put(Event.LOGIN, loginProcesses);

        this.roles.put(AuthenticationDto.class.getSimpleName(), orderRoles);
    }
    @Override
    public void login(EntityModel em) {
        try {
            AuthenticationDto data = (AuthenticationDto) em;
            this.execute(data, Event.LOGIN);
        }catch (RuntimeException | StackOverflowError e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}