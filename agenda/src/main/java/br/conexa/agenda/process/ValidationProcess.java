package br.conexa.agenda.process;

import br.conexa.agenda.model.EntityModel;

@FunctionalInterface
public interface ValidationProcess {
    void process(EntityModel entityModel);
}