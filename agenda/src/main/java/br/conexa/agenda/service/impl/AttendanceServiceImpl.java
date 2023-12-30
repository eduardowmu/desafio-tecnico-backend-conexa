package br.conexa.agenda.service.impl;

import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.enumeration.Event;
import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.Attendance;
import br.conexa.agenda.model.Patient;
import br.conexa.agenda.process.ValidAttendDate;
import br.conexa.agenda.process.ValidPatientDocument;
import br.conexa.agenda.process.ValidationProcess;
import br.conexa.agenda.repository.AttendanceRepository;
import br.conexa.agenda.repository.PatientRepository;
import br.conexa.agenda.service.AttendanceService;
import br.conexa.agenda.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AttendanceServiceImpl extends Facade implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final PatientRepository patientRepository;
    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, PatientRepository patientRepository) {
        this.attendanceRepository = attendanceRepository;
        this.patientRepository = patientRepository;

        this.roles = new HashMap<>();

        //list of roles to SAVE an attendance
        ValidAttendDate va = new ValidAttendDate(this.attendanceRepository);
        ValidPatientDocument vp = new ValidPatientDocument();

        List<ValidationProcess> saveProcesses = new ArrayList<>();
        saveProcesses.add(va);
        saveProcesses.add(vp);

        //Processes roles to SAVE an user
        Map<Event, List<ValidationProcess>> orderRoles = new HashMap<>();
        orderRoles.put(Event.SAVE, saveProcesses);

        this.roles.put(AttendanceDto.class.getSimpleName(), orderRoles);
    }
    @Override
    public Attendance create(AttendanceDto data) {
        try {
            this.execute(data, Event.SAVE);
            Attendance attendance = EntityUtils.toAttendanceFromDto(data);
            Patient createdPatient = this.patientRepository.save(attendance.getPatient());
            attendance.setPatient(createdPatient);
            return this.attendanceRepository.save(attendance);
        } catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}