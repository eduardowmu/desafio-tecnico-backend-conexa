package br.conexa.agenda.service.impl;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.enumeration.Event;
import br.conexa.agenda.mapper.DoctorMapper;
import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.process.ValidDocument;
import br.conexa.agenda.process.ValidPhone;
import br.conexa.agenda.process.ValidSpecialty;
import br.conexa.agenda.process.ValidationProcess;
import br.conexa.agenda.repository.DoctorRepository;
import br.conexa.agenda.repository.SpecialtyRepository;
import br.conexa.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl extends Facade implements UserService {
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
        this.doctorMapper = doctorMapper;

        //list of validations to SAVE an doctor
        ValidDocument vd = new ValidDocument();
        ValidSpecialty vs = new ValidSpecialty(this.specialtyRepository);
        ValidPhone vp = new ValidPhone();
        List<ValidationProcess> saveProcesses = new ArrayList<>();
        saveProcesses.add(vd);
        saveProcesses.add(vs);
        saveProcesses.add(vp);

        //Processes roles to SAVE an doctor
        Map<Event, List<ValidationProcess>> orderRoles = new HashMap<>();
        orderRoles.put(Event.SAVE, saveProcesses);

        this.roles.put(Doctor.class.getSimpleName(), orderRoles);
    }

    @Override
    public void create(EntityModel em) {
        Doctor doctor = (Doctor) em;
        this.doctorRepository.save(doctor);
    }
}