package br.conexa.agenda.service;

import br.conexa.agenda.model.EntityModel;
@FunctionalInterface
public interface LoginService {
    void login(EntityModel em);
}
