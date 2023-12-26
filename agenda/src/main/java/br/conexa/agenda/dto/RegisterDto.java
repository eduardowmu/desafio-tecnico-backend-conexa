package br.conexa.agenda.dto;

import br.conexa.agenda.enumeration.UserRole;
import br.conexa.agenda.model.EntityModel;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record RegisterDto(@NotNull @NotBlank String email, @NotNull @NotBlank String senha,
                          @NotNull @NotBlank String confirmacaoSenha, @NotNull @NotBlank String especialidade,
                          @NotNull @NotBlank String cpf, @NotNull @NotBlank String dataNascimento,
                          @NotNull @NotBlank String telefone) implements EntityModel {
}