package api.login_jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.login_jwt.dto.ApiResponse;
import api.login_jwt.dto.auth.request.RequestAuth;
import api.login_jwt.dto.auth.response.ResponseAuth;
import api.login_jwt.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<ResponseAuth>> login(
            @Valid @RequestBody RequestAuth request) {

        ResponseAuth response = authService.login(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Login exitoso",
                        response));
    }
}
