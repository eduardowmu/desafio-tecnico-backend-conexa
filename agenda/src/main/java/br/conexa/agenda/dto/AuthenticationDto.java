package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;

public record AuthenticationDto(String email, String senha) implements EntityModel {
}
