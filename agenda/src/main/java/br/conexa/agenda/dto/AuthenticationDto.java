package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDto(@NotNull String email, @NotNull String senha) implements EntityModel {
}