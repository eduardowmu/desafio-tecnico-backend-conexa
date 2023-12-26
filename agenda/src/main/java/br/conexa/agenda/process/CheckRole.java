package br.conexa.agenda.process;

import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckRole implements ValidationProcess {
    private final SpecialtyRepository specialtyRepository;
    @Override
    public void process(EntityModel entityModel) {
        Doctor doctor = (Doctor) entityModel;
        if(!this.specialtyRepository.findByName(doctor.getSpecialty()).isPresent()) {
            throw new IllegalArgumentException("Não existe esta especialidade em nossa lista " + doctor.getSpecialty());
        }


    }
}
