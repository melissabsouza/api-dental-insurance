package fiap.tds.dental.insurance.api.entity;

import fiap.tds.dental.insurance.api.enums.StatusUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotBlank(message = "O email é obrigatório")
    @Column(name = "email_usuario", length = 100, nullable = false)
    private String email;

    @Size(min = 5, message = "A senha deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A senha não pode ser vazia")
    @NotNull(message = "senha não pode ser nula")
    @Column(name = "senha_usuario", length = 100, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_usuario", nullable = false)
    private StatusUsuario status;
}
