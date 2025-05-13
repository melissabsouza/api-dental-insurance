package fiap.tds.dental.insurance.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;

    @Size(min = 5, message = "A rua deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A rua não pode ser vazia")
    @NotNull(message = "rua não pode ser nula")
    private String rua;

    @NotNull(message = "numero não pode ser nulo")
    @Column(name = "numero_endereco", nullable = false)
    private int numero;

    private String complemento;

    @Size(min = 5, message = "O bairro deve ter pelo menos 5 caracteres")
    @NotBlank(message = "O bairro não pode ser vazio")
    @NotNull(message = "O bairro não pode ser nulo")
    private String bairro;

    @Size(min = 5, message = "A cidade deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A cidade não pode ser vazia")
    @NotNull(message = "A cidade não pode ser nula")
    private String cidade;

    @NotNull(message = "estado não pode ser nulo")
    @NotBlank(message = "estado não pode ser nulo")
    private String estado;

    @Pattern(regexp = "\\d{5}-?\\d{3}",
            message = "Use XXXXX-XXX")
    @NotNull(message = "cep não pode ser nulo")
    private String cep;
}
