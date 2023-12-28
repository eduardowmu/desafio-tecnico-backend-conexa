package br.conexa.agenda.service.impl;

import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.model.Attendance;
import br.conexa.agenda.repository.AttendanceRepository;
import br.conexa.agenda.repository.PatientRepository;
import br.conexa.agenda.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class AttendanceServiceImpl extends Facade implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final PatientRepository patientRepository;
    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, PatientRepository patientRepository) {
        this.attendanceRepository = attendanceRepository;
        this.patientRepository = patientRepository;

        this.roles = new HashMap<>();
    }
    @Override
    public Attendance create(AttendanceDto data) {
        return null;
    }
}
