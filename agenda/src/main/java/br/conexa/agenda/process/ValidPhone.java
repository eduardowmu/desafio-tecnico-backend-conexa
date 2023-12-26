package br.conexa.agenda.process;

import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.EntityModel;

public class ValidPhone implements ValidationProcess{
    @Override
    public void process(EntityModel entityModel) {
        Doctor doctor = (Doctor)entityModel;
        if(doctor.getPhone().length() < 10 || doctor.getPhone().length() > 11) {
            throw new IllegalArgumentException("Numero de telefone invalido " + doctor.getPhone());
        }
    }
}
