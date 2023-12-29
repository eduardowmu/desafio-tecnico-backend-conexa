package br.conexa.agenda.utils;

import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.dto.AuthenticationDto;
import br.conexa.agenda.dto.PacienteDto;
import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.model.Attendance;
import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.Patient;
import br.conexa.agenda.model.User;

public class EntityUtils {
    public static Doctor toDoctorFromRegister(RegisterDto data) {
        Doctor doctor = new Doctor();
        doctor.setSpecialty(data.especialidade());
        doctor.setBirthDate(FormatUtils.toDate(data.dataNascimento()));
        doctor.setPhone(FormatUtils.toWithoutFormat(data.telefone()));
        doctor.setCpf(FormatUtils.toWithoutFormat(data.cpf()));
        return doctor;
    }

    public static User toUserFromAuth(AuthenticationDto authenticationDto) {
        User user = new User();
        user.setUserName(authenticationDto.userName());
        user.setPassword(authenticationDto.password());
        return user;
    }

    public static Attendance toAttendanceFromDto(AttendanceDto data) {
        Attendance attendance = new Attendance();
        attendance.setDateTime(FormatUtils.toDateTime(data.dataHora()));
        Patient patient = new Patient();
        patient.setName(data.paciente().nome());
        patient.setCpf(FormatUtils.toWithoutFormat(data.paciente().cpf()));
        attendance.setPatient(patient);
        return attendance;
    }

    public static AttendanceDto toAttendanceDto(Attendance attendance) {
        AttendanceDto attendanceDto = new AttendanceDto(FormatUtils.dateToText(attendance.getDateTime()),
                new PacienteDto(attendance.getPatient().getName(), attendance.getPatient().getCpf()));
        return attendanceDto;
    }
}