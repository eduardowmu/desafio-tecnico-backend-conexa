package br.conexa.agenda.process;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.utils.FormatUtils;

public class ValidPhone implements ValidationProcess{
    @Override
    public void process(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        if(FormatUtils.toWithoutFormat(data.telefone()).length() < 10 ||
                FormatUtils.toWithoutFormat(data.telefone()).length() > 11) {
            throw new IllegalArgumentException("Numero de telefone invalido " + data.telefone());
        }
    }
}