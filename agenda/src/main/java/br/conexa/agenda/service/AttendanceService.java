package br.conexa.agenda.service;

import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.model.Attendance;

import java.util.List;

public interface AttendanceService {
    Attendance create(AttendanceDto data);
    List<Attendance> listAll();
}