package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.Patient;
import br.conexa.agenda.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgendaDto implements EntityModel {
    private Patient patient;
    private String dataHora;
    private User user;
}