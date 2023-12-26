package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDto implements EntityModel {
    private String name;
    private String cpf;
}