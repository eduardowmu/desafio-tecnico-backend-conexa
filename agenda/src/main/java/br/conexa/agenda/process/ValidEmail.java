package br.conexa.agenda.process;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.model.EntityModel;

public class ValidEmail implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        if(!data.userName().contains("@conexa.com")) {
            throw new IllegalArgumentException("Usuário inválido" + data.userName());
        }
    }
}