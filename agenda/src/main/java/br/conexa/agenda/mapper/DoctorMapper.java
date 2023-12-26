package br.conexa.agenda.mapper;

import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.utils.FormatUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = FormatUtils.class)
public interface DoctorMapper {
    @Mapping(target = "specialty", source = "especialidade")
    @Mapping(target = "cpf", expression = "java(FormatUtils.withoutFormat(dto.cpf()))")
    @Mapping(target = "birthDate", expression = "java(FormatUtils.toLocalDate(dto.dataNascimento()))")
    @Mapping(target = "phone", expression = "java(FormatUtils.withoutFormat(dto.telefone()))")
    Doctor toDoctor(RegisterDto dto);
}