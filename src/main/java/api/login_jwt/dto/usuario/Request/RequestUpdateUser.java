package api.login_jwt.dto.usuario.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateUser {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Size(max = 150, message = "El email no puede tener más de 150 caracteres")
    @Email(message = "El email debe ser válido")
    private String email;

    private String password;
}
