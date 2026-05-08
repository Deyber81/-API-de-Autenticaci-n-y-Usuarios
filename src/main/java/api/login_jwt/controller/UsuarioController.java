package api.login_jwt.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.login_jwt.dto.ApiResponse;
import api.login_jwt.dto.usuario.Request.RequestCreateUser;
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

        URI location = URI.create("/api/v1/usuarios/" + usuarioResponse.getId());

        return ResponseEntity
                .created(location)
                .body(ApiResponse.success("Usuario creado correctamente", usuarioResponse));
    }
}