package api.login_jwt.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.login_jwt.dto.pagination.PaginatedResponse;
import api.login_jwt.dto.pagination.PaginationResponse;
import api.login_jwt.dto.usuario.Request.RequestCreateUser;
import api.login_jwt.dto.usuario.response.UsuarioResponse;
import api.login_jwt.entity.TUsuario;
import api.login_jwt.repository.RepoUsuario;
import api.login_jwt.validators.UsuarioValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public PaginatedResponse<List<UsuarioResponse>> listarUsuarios(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<TUsuario> usuariosPage = repoUsuario.findAll(pageable);

        List<UsuarioResponse> usuarios = usuariosPage.getContent()
                .stream()
                .map(usuario -> new UsuarioResponse(
                        String.valueOf(usuario.getId()),
                        usuario.getNombre(),
                        usuario.getEmail()))
                .toList();

        PaginationResponse pagination = new PaginationResponse(
                usuariosPage.getNumber(),
                usuariosPage.getSize(),
                usuariosPage.getTotalElements(),
                usuariosPage.getTotalPages(),
                usuariosPage.isFirst(),
                usuariosPage.isLast());

        return new PaginatedResponse<>(usuarios, pagination);
    }
}