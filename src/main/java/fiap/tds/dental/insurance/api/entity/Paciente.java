package fiap.tds.dental.insurance.api.entity;

import fiap.tds.dental.insurance.api.enums.TipoGenero;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_PACIENTE")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message = "Formato de CPF inválido, use 12345678900")
    @Column(name = "cpf_paciente", unique = true, nullable = false)
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    @Column(name = "nome_paciente", length = 100, nullable = false)
    private String nome;

    @NotNull(message = "Data não pode ser nula")
    private LocalDate dataNascimento;

    @Column(name = "genero_paciente", length = 100, nullable = false)
    @NotNull(message = "gênero não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private TipoGenero genero;

    @OneToOne
    @JoinColumn(name = "cnpj_clinica", referencedColumnName = "cnpj_clinica", nullable = false)
    private Clinica clinica;

    @OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco", nullable = false)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "id_telefone", referencedColumnName = "id_telefone", nullable = false)
    private Telefone telefone;
}
