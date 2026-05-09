package api.login_jwt.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.login_jwt.dto.pagination.PaginatedResponse;
import api.login_jwt.dto.pagination.PaginationResponse;
import api.login_jwt.dto.usuario.Request.RequestCreateUser;
import api.login_jwt.dto.usuario.Request.RequestUpdateUser;
import api.login_jwt.dto.usuario.response.UsuarioResponse;
import api.login_jwt.entity.TUsuario;
import api.login_jwt.exception.ResourceNotFoundException;
import api.login_jwt.mapper.UsuarioMapper;
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
        private final UsuarioMapper usuarioMapper;

        public UsuarioResponse crearUsuario(RequestCreateUser request) {
                usuarioValidator.validarCreacion(request);

                TUsuario usuario = usuarioMapper.toEntity(request);
                usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));

                return usuarioMapper.toDto(repoUsuario.save(usuario));
        }

        @Transactional(readOnly = true)
        public PaginatedResponse<List<UsuarioResponse>> listarUsuarios(int page, int size) {
                Pageable pageable = PageRequest.of(page, size);
                Page<TUsuario> usuariosPage = repoUsuario.findAll(pageable);

                List<UsuarioResponse> usuarios = usuariosPage.getContent()
                                .stream()
                                .map(usuarioMapper::toDto)
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

        @Transactional(readOnly = true)
        public UsuarioResponse buscarPorId(String id) {
                return usuarioMapper.toDto(findOrThrow(id));
        }

        @Transactional
        public UsuarioResponse actualizarUsuario(String id, RequestUpdateUser request) {
                TUsuario usuario = findOrThrow(id);
                usuarioValidator.validarActualizacion(id, request);

                usuarioMapper.updateUser(request, usuario);

                if (request.getPassword() != null && !request.getPassword().isBlank()) {
                        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
                }

                return usuarioMapper.toDto(repoUsuario.save(usuario));
        }

        @Transactional
        public void eliminarUsuario(String id) {
                repoUsuario.delete(findOrThrow(id));
        }

        private TUsuario findOrThrow(String id) {
                return repoUsuario.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        }

}