package br.conexa.agenda.repository;

import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}