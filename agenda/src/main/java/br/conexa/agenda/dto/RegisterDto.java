package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;
import org.antlr.v4.runtime.misc.NotNull;

public record RegisterDto(@NotNull String email, @NotNull String senha, @NotNull String confirmacaoSenha,
                          @NotNull String especialidade, @NotNull String cpf, @NotNull String dataNascimento,
                          @NotNull String telefone) implements EntityModel {
}