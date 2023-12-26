package br.conexa.agenda.service.impl;

import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.process.ValidationProcess;

import java.util.List;
import java.util.Map;

public abstract class Facade {
    protected Map<String, Map<String, List<ValidationProcess>>> rules;

    protected void execute(EntityModel em, String event) {
        Map<String, List<ValidationProcess>> operationRoles = this.rules.get(em.getClass().getSimpleName());
        if(operationRoles != null) {
            List<ValidationProcess> rolesList = operationRoles.get(event);
            if(rolesList != null) {
                for(ValidationProcess role : rolesList) {
                    role.process(em);
                }
            }
        }
    }
}