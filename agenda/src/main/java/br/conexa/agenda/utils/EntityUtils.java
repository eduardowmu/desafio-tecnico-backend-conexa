package br.conexa.agenda.utils;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.model.Doctor;

public class EntityUtils {
    public static Doctor toDoctorFromRegister(RegisterDto data) {
        Doctor doctor = new Doctor();

        return doctor;
    }
}