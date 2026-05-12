package api.login_jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import api.login_jwt.dto.auth.response.ResponseAuth;
import api.login_jwt.entity.TUsuario;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    // Response Auth
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    @Mapping(target = "expiresIn", ignore = true)
    ResponseAuth toDto(TUsuario usuario);

    // Request Auth
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "emailVerificado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    TUsuario toEntity(ResponseAuth request);
}
