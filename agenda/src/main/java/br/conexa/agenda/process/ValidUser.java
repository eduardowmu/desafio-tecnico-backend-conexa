package br.conexa.agenda.process;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;
import br.conexa.agenda.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidUser implements ValidationProcess {
    private final UserRepository repository;
    @Override
    public void process(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        if(this.repository.findByUserName(data.userName()).isPresent())
            throw new IllegalArgumentException("Usuário já existe " + data.userName());
    }
}