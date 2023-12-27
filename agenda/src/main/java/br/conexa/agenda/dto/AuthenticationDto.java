package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDto(@NotNull String userName, @NotNull String password) implements EntityModel {
}