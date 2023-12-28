package br.conexa.agenda.process;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.utils.FormatUtils;

import java.time.LocalDate;
import java.time.Period;

public class ValidBirthDate implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        if(FormatUtils.toDate(data.dataNascimento()).isAfter(LocalDate.now()) ||
                !this.isEnableDoctor(FormatUtils.toDate(data.dataNascimento()))) {
            throw new IllegalArgumentException("Data de nascimento inválido " + data.dataNascimento());
        }
    }

    private Boolean isEnableDoctor(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() < 18 ?
                Boolean.FALSE : Boolean.TRUE;
    }
}