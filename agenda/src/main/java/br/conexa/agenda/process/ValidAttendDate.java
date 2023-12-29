package br.conexa.agenda.process;

import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.repository.AttendanceRepository;
import br.conexa.agenda.utils.FormatUtils;

import java.time.LocalDateTime;

public class ValidAttendDate implements ValidationProcess {
    private final AttendanceRepository attendanceRepository;
    public ValidAttendDate(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public void process(EntityModel entityModel) {
        AttendanceDto data = (AttendanceDto) entityModel;
        if(FormatUtils.toDateTime(data.dataHora()).isBefore(LocalDateTime.now()) || data.dataHora().equals(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível agendar nesta data passada" + data.dataHora());
        } else if(this.attendanceRepository.findByDateTime(FormatUtils.toDateTime(data.dataHora())).isPresent()) {
            throw new IllegalArgumentException("Já existe agenda nesta data " + data.dataHora());
        }
    }
}