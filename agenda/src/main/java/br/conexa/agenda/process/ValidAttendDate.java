package br.conexa.agenda.process;

import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.model.EntityModel;

import java.time.LocalDateTime;

public class ValidAttendDate implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        AttendanceDto data = (AttendanceDto) entityModel;
        if(data.dataHora().isBefore(LocalDateTime.now()) || data.dataHora().equals(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível agendar nesta data " + data.dataHora());
        }
    }
}