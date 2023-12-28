package br.conexa.agenda.dto;

import jakarta.validation.constraints.NotNull;

public record PacienteDto(@NotNull String nome, @NotNull String cpf) {
}