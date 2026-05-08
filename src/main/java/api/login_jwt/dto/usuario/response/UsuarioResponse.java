package api.login_jwt.dto.usuario.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponse {

    private String id;
    private String nombre;
    private String email;
}