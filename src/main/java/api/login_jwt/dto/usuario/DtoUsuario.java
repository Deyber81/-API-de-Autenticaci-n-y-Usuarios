package api.login_jwt.dto.usuario;

import api.login_jwt.entity.TUsuario.Estado;
import api.login_jwt.entity.TUsuario.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUsuario {
    private String id;

    private String nombre;

    private String email;

    private Rol rol;

    private Estado estado;

    private Boolean emailVerificado;

}
