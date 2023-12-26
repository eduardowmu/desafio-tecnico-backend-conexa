package br.conexa.agenda.process;

import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.EntityModel;

import java.time.LocalDate;
import java.time.Period;

public class ValidBirthDate implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        Doctor doctor = (Doctor) entityModel;
        if(doctor.getBirthDate().isAfter(LocalDate.now()) || !this.isEnableDoctor(doctor.getBirthDate())) {
            throw new IllegalArgumentException("Data de nascimento inválido " + doctor.getBirthDate());
        }
    }

    private Boolean isEnableDoctor(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() < 18 ?
                Boolean.FALSE : Boolean.TRUE;
    }
}