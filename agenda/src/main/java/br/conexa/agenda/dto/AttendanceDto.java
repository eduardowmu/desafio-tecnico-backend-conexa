package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record AttendanceDto(@NotNull String dataHora, @NotNull PacienteDto paciente) implements EntityModel {
}