package br.conexa.agenda.service;

import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.model.Attendance;

@FunctionalInterface
public interface AttendanceService {
    Attendance create(AttendanceDto data);
}