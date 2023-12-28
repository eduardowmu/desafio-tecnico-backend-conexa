package br.conexa.agenda.service.impl;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.enumeration.Event;
import br.conexa.agenda.enumeration.UserRole;
import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;
import br.conexa.agenda.process.*;
import br.conexa.agenda.repository.DoctorRepository;
import br.conexa.agenda.repository.SpecialtyRepository;
import br.conexa.agenda.repository.UserRepository;
import br.conexa.agenda.service.RegisterService;
import br.conexa.agenda.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl extends Facade implements RegisterService {
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository, DoctorRepository doctorRepository,
                               SpecialtyRepository specialtyRepository) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;

        this.roles = new HashMap<>();

        //list of roles to SAVE an user
        ValidEmail ve = new ValidEmail();
        ValidUser vu = new ValidUser(this.userRepository);
        ValidDocument vd = new ValidDocument();
        ValidSpecialty vs = new ValidSpecialty(this.specialtyRepository);
        ValidPhone vp = new ValidPhone();
        ValidPassword vpass = new ValidPassword();

        List<ValidationProcess> saveProcesses = new ArrayList<>();
        saveProcesses.add(ve);
        saveProcesses.add(vu);
        saveProcesses.add(vd);
        saveProcesses.add(vs);
        saveProcesses.add(vp);
        saveProcesses.add(vpass);

        //Processes roles to SAVE an user
        Map<Event, List<ValidationProcess>> orderRoles = new HashMap<>();
        orderRoles.put(Event.SAVE, saveProcesses);

        this.roles.put(RegisterDto.class.getSimpleName(), orderRoles);
    }
    @Override
    public void create(EntityModel em) {
        try {
            RegisterDto data = (RegisterDto) em;
            this.execute(data, Event.SAVE);

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(null, data.userName(), encryptedPassword, data.especialidade() != null &&
                    !data.especialidade().isEmpty() ? UserRole.ADMIN : UserRole.USER);

            User createUser = this.userRepository.save(newUser);

            Doctor doctor = EntityUtils.toDoctorFromRegister(data);
            doctor.setUser(createUser);

            this.doctorRepository.save(doctor);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}