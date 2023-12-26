package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;

public record AuthenticationDto(String userName, String password) implements EntityModel {
}
