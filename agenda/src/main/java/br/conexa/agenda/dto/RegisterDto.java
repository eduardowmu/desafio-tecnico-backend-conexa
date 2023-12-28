package br.conexa.agenda.dto;

import br.conexa.agenda.enumeration.UserRole;
import br.conexa.agenda.model.EntityModel;
import org.antlr.v4.runtime.misc.NotNull;

public record RegisterDto(@NotNull String userName, @NotNull String password, @NotNull String confirmacaoSenha,
                          @NotNull String especialidade, @NotNull String cpf, @NotNull String dataNascimento,
                          @NotNull String telefone) implements EntityModel {
}