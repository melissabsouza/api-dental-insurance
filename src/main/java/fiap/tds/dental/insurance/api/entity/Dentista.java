package fiap.tds.dental.insurance.api.entity;

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
@Table(name = "T_CHALLENGE_DENTISTA")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dentista", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message = "Formato de CPF inválido, use 12345678900")
    @Column(name = "cpf_dentista", unique = true, nullable = false)
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    @Column(name = "nome_dentista", length = 100, nullable = false)
    private String nome;

    @NotBlank(message = "CRO é obrigatório")
    @NotNull(message = "CRO não pode ser nulo")
    @Size(min = 5, message = "CRO deve ter pelo menos 5 caracteres")
    @Column(name = "cro_dentista", unique = true, length = 100, nullable = false)
    private String cro;

    @NotBlank(message = "Especialidade é obrigatória")
    @NotNull(message = "Especialidade não pode ser nula")
    @Size(min = 3, message = "Especialidade deve ter pelo menos 10 caracteres")
    @Column(name = "especialidade", length = 100, nullable = false)
    private String especialidade;

    @NotBlank(message = "O email é obrigatório")
    @Column(name = "email_dentista", length = 100, unique = true, nullable = false)
    private String email;

    @NotNull(message = "Data não pode ser nula")
    @Column(name = "data_contratacao", nullable = false)
    private LocalDate dataContratacao;

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
