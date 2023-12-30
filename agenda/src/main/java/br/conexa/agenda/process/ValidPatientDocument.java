package br.conexa.agenda.process;

import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.dto.PacienteDto;
import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.EntityModel;

import static br.conexa.agenda.utils.FormatUtils.toWithoutFormat;
import static br.conexa.agenda.utils.FormatUtils.validCpf;

public class ValidPatientDocument implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        AttendanceDto dto = (AttendanceDto)entityModel;
        PacienteDto data = dto.paciente();
        if(!validCpf(toWithoutFormat(data.cpf()))) {
            throw new IllegalArgumentException("CPF inválido " + data.cpf());
        }
    }
}