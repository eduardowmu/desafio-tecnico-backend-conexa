package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorDto implements EntityModel {
    @NotNull
    @NotBlank
    private String especialidade;
    @NotNull
    @NotBlank
    private String cpf;
    @NotNull
    @NotBlank
    private String dataNascimento;
    @NotNull
    @NotBlank
    private String telefone;
}