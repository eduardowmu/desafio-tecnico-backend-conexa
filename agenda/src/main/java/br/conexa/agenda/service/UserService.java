package br.conexa.agenda.service;

import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;

@FunctionalInterface
public interface UserService {
    void create(EntityModel user);
}