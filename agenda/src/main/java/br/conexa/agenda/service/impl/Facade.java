package br.conexa.agenda.service.impl;

import br.conexa.agenda.enumeration.Event;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.process.ValidationProcess;

import java.util.List;
import java.util.Map;

public abstract class Facade {
    protected Map<String, Map<Event, List<ValidationProcess>>> roles;

    protected void execute(EntityModel em, Event event) {
        Map<Event, List<ValidationProcess>> operationRoles = this.roles.get(em.getClass().getSimpleName());
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