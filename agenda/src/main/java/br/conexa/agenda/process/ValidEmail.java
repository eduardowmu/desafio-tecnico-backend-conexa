package br.conexa.agenda.process;

import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;

public class ValidEmail implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        User user = (User)entityModel;
        if(!user.getUsername().contains("@conexa.com")) {
            throw new IllegalArgumentException("Usuário inválido " + user.getUsername());
        }
    }
}