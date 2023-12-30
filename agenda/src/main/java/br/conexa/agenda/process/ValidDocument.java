package br.conexa.agenda.process;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.EntityModel;

import static br.conexa.agenda.utils.FormatUtils.toWithoutFormat;
import static br.conexa.agenda.utils.FormatUtils.validCpf;

public class ValidDocument implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        if(!validCpf(toWithoutFormat(data.cpf()))) {
            throw new IllegalArgumentException("CPF inválido " + data.cpf());
        }
    }
}