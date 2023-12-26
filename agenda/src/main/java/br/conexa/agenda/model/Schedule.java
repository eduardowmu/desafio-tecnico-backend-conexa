package br.conexa.agenda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private LocalDate dateTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}