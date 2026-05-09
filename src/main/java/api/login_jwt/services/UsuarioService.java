package api.login_jwt.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.login_jwt.dto.usuario.Request.RequestCreateUser;
import api.login_jwt.dto.usuario.response.UsuarioResponse;
import api.login_jwt.entity.TUsuario;
import api.login_jwt.repository.RepoUsuario;
import api.login_jwt.validators.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final RepoUsuario repoUsuario;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioValidator usuarioValidator;

    public UsuarioResponse crearUsuario(RequestCreateUser request) {

        usuarioValidator.validarCreacion(request);

        TUsuario usuario = new TUsuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        TUsuario usuarioGuardado = repoUsuario.save(usuario);

        return new UsuarioResponse(
                String.valueOf(usuarioGuardado.getId()),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getEmail());
    }

    public Page<UsuarioResponse> listarUsuarios(Pageable pageable) {
        return repoUsuario.findAll(pageable).map(usuario -> new UsuarioResponse(
                String.valueOf(usuario.getId()),
                usuario.getNombre(),
                usuario.getEmail()));
    }
}