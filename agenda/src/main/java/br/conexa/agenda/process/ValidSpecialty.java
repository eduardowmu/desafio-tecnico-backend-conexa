package br.conexa.agenda.process;

import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.EntityModel;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidSpecialty implements ValidationProcess {
    private final SpecialtyRepository specialtyRepository;
    @Override
    public void process(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;
        if(!this.specialtyRepository.findByName(data.especialidade().toUpperCase()).isPresent()) {
            throw new IllegalArgumentException("Não existe esta especialidade em nossa lista " + data.especialidade());
        }
    }
}