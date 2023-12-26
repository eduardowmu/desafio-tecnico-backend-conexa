package br.conexa.agenda.service.impl;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.enumeration.Event;
import br.conexa.agenda.enumeration.UserRole;
import br.conexa.agenda.mapper.DoctorMapper;
import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.EntityModel;
import br.conexa.agenda.model.User;
import br.conexa.agenda.process.ValidEmail;
import br.conexa.agenda.process.ValidUser;
import br.conexa.agenda.process.ValidationProcess;
import br.conexa.agenda.repository.UserRepository;
import br.conexa.agenda.service.UserService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl extends Facade implements UserService {
    private final UserRepository userRepository;
    private final DoctorServiceImpl doctorService;
    private final DoctorMapper doctorMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DoctorServiceImpl doctorService, DoctorMapper doctorMapper) {
        this.userRepository = userRepository;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;

        this.roles = new HashMap<>();

        //list of roles to SAVE an user
        ValidEmail ve = new ValidEmail();
        ValidUser vu = new ValidUser(this.userRepository);

        List<ValidationProcess> saveProcesses = new ArrayList<>();
        saveProcesses.add(ve);
        saveProcesses.add(vu);

        //Processes roles to SAVE an user
        Map<Event, List<ValidationProcess>> orderRoles = new HashMap<>();
        orderRoles.put(Event.SAVE, saveProcesses);

        this.roles.put(User.class.getSimpleName(), orderRoles);
    }

    @Override
    public void create(EntityModel entityModel) {
        RegisterDto data = (RegisterDto) entityModel;

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        User newUser = new User(null, data.email(), encryptedPassword, data.especialidade() != null &&
                !data.especialidade().isEmpty() ? UserRole.ADMIN : UserRole.USER);

        this.execute(newUser, Event.SAVE);

        Doctor doctor = this.doctorMapper.toDoctor(data);
        this.doctorService.execute(doctor, Event.SAVE);

        this.userRepository.save(newUser);

        doctor.setUser(this.userRepository.findByUserName(newUser.getUsername()).get());
        this.doctorService.create(doctor);
    }
}
