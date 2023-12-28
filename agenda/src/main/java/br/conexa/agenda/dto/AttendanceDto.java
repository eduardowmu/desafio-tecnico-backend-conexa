package br.conexa.agenda.dto;

import br.conexa.agenda.model.EntityModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record AttendanceDto(@NotNull @DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss") LocalDateTime dataHora,
                            @NotNull PacienteDto paciente) implements EntityModel {
}