package api.login_jwt.validators;

import org.springframework.stereotype.Component;

import api.login_jwt.dto.usuario.Request.RequestCreateUser;
import api.login_jwt.exception.BusinessValidation;
import api.login_jwt.repository.RepoUsuario;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final RepoUsuario repoUsuario;

    public void validarCreacion(RequestCreateUser request) {

        BusinessValidation validation = new BusinessValidation();

        if (repoUsuario.existsByEmail(request.getEmail())) {
            validation.addError("email", "El correo electrónico ya está registrado");
        }

        validation.throwIfHasErrors("Errores de validación de negocio");
    }
}