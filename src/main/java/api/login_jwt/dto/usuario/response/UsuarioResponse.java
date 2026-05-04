package api.login_jwt.dto.usuario.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    private String id;
    private String nombre;
    private String email;
}