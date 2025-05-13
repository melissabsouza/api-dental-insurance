package fiap.tds.dental.insurance.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Size(min = 5, message = "A rua deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A rua não pode ser vazia")
    @NotNull(message = "rua não pode ser nula")
    @Column(name = "rua_endereco", length = 100, nullable = false)
    private String rua;


    @NotNull(message = "numero não pode ser nulo")
    @Column(name = "numero_endereco", nullable = false)
    private int numero;

    @Column(name = "complemento_endereco", length = 100, nullable = false)
    private String complemento;


    @Size(min = 5, message = "O bairro deve ter pelo menos 5 caracteres")
    @NotBlank(message = "O bairro não pode ser vazio")
    @NotNull(message = "O bairro não pode ser nulo")
    @Column(name = "bairro_endereco", length = 100, nullable = false)
    private String bairro;

    @Size(min = 5, message = "A cidade deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A cidade não pode ser vazia")
    @NotNull(message = "A cidade não pode ser nula")
    @Column(name = "cidade_endereco", length = 100, nullable = false)
    private String cidade;

    @NotNull(message = "estado não pode ser nulo")
    @NotBlank(message = "estado não pode ser nulo")
    @Column(name = "estado_endereco", length = 100, nullable = false)
    private String estado;

    @Pattern(regexp = "\\d{5}-?\\d{3}",
            message = "Use XXXXX-XXX")
    @NotNull(message = "cep não pode ser nulo")
    @Column(name = "cep", nullable = false)
    private String cep;

}
