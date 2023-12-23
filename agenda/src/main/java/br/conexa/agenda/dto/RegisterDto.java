package br.conexa.agenda.dto;

import br.conexa.agenda.enumeration.UserRole;
import org.antlr.v4.runtime.misc.NotNull;

public record RegisterDto(@NotNull String userName, @NotNull String password, @NotNull UserRole role) {
}