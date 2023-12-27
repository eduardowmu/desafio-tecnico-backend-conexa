package br.conexa.agenda.process;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;
import br.conexa.agenda.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidUser implements ValidationProcess {
    private final UserRepository repository;
    @Override
    public void process(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        if(this.repository.findByUserName(data.email()).isPresent())
            throw new EntityExistsException("Usuário já existe " + data.email());
    }
}