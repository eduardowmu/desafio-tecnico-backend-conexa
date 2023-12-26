package br.conexa.agenda.mapper;

import br.conexa.agenda.dto.AuthenticationDto;
import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.model.User;
import br.conexa.agenda.utils.FormatUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userName", source = "email")
    @Mapping(target = "password", source = "senha")
    User toUserFromRegister(RegisterDto dto);

    @Mapping(target = "userName", source = "email")
    @Mapping(target = "password", source = "senha")
    User toUserFromAuth(AuthenticationDto dto);
}