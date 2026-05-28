package api.login_jwt.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.login_jwt.dto.auth.request.RequestAuth;
import api.login_jwt.dto.auth.response.ResponseAuth;
import api.login_jwt.entity.TUsuario;
import api.login_jwt.exception.UnauthorizedException;
import api.login_jwt.mapper.AuthMapper;
import api.login_jwt.repository.RepoUsuario;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RepoUsuario userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public ResponseAuth login(RequestAuth request) {
        TUsuario user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Credenciales incorrectas");
        }

        return authMapper.toDto(user);
    }
}
