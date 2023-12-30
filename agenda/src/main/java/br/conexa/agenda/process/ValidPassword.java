package br.conexa.agenda.process;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.EntityModel;

public class ValidPassword implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        if(!data.password().equals(data.confirmacaoSenha())) {
            throw new IllegalArgumentException("As senhas não batem!");
        }
    }
}