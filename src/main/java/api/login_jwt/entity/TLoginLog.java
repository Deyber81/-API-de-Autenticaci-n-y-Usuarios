package api.login_jwt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "login_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TLoginLog {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)", nullable = false, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private TUsuario usuario;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "ip", length = 45, nullable = false)
    private String ip;

    @Column(name = "user_agent", columnDefinition = "TEXT", nullable = false)
    private String userAgent;
    private Boolean exitoso;

    @Column(name = "mensaje", length = 255, nullable = false)
    private String mensaje;

    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        fecha = LocalDateTime.now();
    }
}
