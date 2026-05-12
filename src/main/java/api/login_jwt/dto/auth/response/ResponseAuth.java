package api.login_jwt.dto.auth.response;

import api.login_jwt.entity.TUsuario.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseAuth {
    private String id;
    private String nombre;
    private String email;
    private Rol rol;
    private String token;
    private String refreshToken;

    private Long expiresIn;
}
