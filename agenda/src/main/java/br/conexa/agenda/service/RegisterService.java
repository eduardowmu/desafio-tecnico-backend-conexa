package br.conexa.agenda.service;

import br.conexa.agenda.model.User;

import br.conexa.agenda.model.EntityModel;

@FunctionalInterface
public interface RegisterService {
    void create(EntityModel em);
}