package br.conexa.agenda.utils;

import br.conexa.agenda.dto.AuthenticationDto;
import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.model.Doctor;
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
        user.setUserName(authenticationDto.email());
        user.setPassword(authenticationDto.senha());
        return user;
    }
}