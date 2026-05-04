package api.login_jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import api.login_jwt.dto.usuario.Request.RequestCreateUser;
import api.login_jwt.dto.usuario.Request.RequestUpdateUser;
import api.login_jwt.dto.usuario.response.UsuarioResponse;
import api.login_jwt.entity.TUsuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    // RESPOSE USER
    UsuarioResponse toDto(TUsuario usuario);

    // REGISTER USER
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "emailVerificado", constant = "false")
    @Mapping(target = "rol", constant = "USER")
    TUsuario toEntity(RequestCreateUser request);

    // USER UPDATE
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    void updateUser(RequestUpdateUser dto, @MappingTarget TUsuario entity);
}
