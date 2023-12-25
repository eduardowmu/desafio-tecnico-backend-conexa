package br.conexa.agenda.dto;

import br.conexa.agenda.enumeration.UserRole;
import org.antlr.v4.runtime.misc.NotNull;

public record RegisterDto(@NotNull String email, @NotNull String senha, @NotNull String confirmacaoSenha, @NotNull UserRole role) {
}