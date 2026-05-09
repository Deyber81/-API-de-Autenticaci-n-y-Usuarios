package api.login_jwt.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import api.login_jwt.dto.ApiResponse;
import api.login_jwt.dto.pagination.PaginatedResponse;
import api.login_jwt.dto.usuario.Request.RequestCreateUser;
import api.login_jwt.dto.usuario.Request.RequestUpdateUser;
import api.login_jwt.dto.usuario.response.UsuarioResponse;
import api.login_jwt.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

        private final UsuarioService usuarioService;

        @PostMapping
        public ResponseEntity<ApiResponse<UsuarioResponse>> crearUsuario(
                        @Valid @RequestBody RequestCreateUser request) {

                UsuarioResponse usuarioResponse = usuarioService.crearUsuario(request);

                URI location = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(usuarioResponse.getId())
                                .toUri();

                return ResponseEntity
                                .created(location)
                                .body(ApiResponse.success("Usuario creado correctamente", usuarioResponse));
        }

        @GetMapping
        public ResponseEntity<ApiResponse<PaginatedResponse<List<UsuarioResponse>>>> listarUsuarios(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {

                PaginatedResponse<List<UsuarioResponse>> response = usuarioService.listarUsuarios(page, size);

                return ResponseEntity.ok(
                                ApiResponse.success("Usuarios listados correctamente", response));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<UsuarioResponse>> buscarPorId(
                        @PathVariable String id) {

                UsuarioResponse response = usuarioService.buscarPorId(id);

                return ResponseEntity.ok(
                                ApiResponse.success("Usuario encontrado", response));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<UsuarioResponse>> actualizarUsuario(
                        @PathVariable String id,
                        @Valid @RequestBody RequestUpdateUser request) {

                UsuarioResponse response = usuarioService.actualizarUsuario(id, request);

                return ResponseEntity.ok(
                                ApiResponse.success("Usuario actualizado correctamente", response));
        }

}