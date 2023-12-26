package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;

public record LoginResponseDto(String token) implements EntityModel {
}