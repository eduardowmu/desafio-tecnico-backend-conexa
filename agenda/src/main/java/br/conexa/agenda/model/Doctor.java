package br.conexa.agenda.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Doctor implements EntityModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String specialty;
    @Column(length = 11)
    private String cpf;
    private LocalDate birthDate;
    @Column(length = 11)
    private String phone;
}