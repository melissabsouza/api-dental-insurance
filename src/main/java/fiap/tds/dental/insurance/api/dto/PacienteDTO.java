package fiap.tds.dental.insurance.api.dto;

import fiap.tds.dental.insurance.api.enums.TipoGenero;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PacienteDTO {

    private Long id;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message = "Formato de CPF inválido, use 12345678900")
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotNull(message = "Data não pode ser nula")
    private LocalDate dataNascimento;

    @NotNull(message = "gênero não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private TipoGenero genero;

    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})",
            message = "Use 00.000.000/0000-00 or 00000000000000")
    @Valid
    private String clinicaCnpj;

    @Valid
    private EnderecoDTO endereco;
    @Valid
    private TelefoneDTO telefone;
}
