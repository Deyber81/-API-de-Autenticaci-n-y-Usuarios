package api.login_jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.login_jwt.entity.TUsuario;

@Repository
public interface RepoUsuario extends JpaRepository<TUsuario, String> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, String id);

    Optional<TUsuario> findById(String id);

}
