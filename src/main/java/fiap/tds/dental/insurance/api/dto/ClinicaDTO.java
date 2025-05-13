package fiap.tds.dental.insurance.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClinicaDTO {

    private Long id;

    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})",
            message = "Use 00.000.000/0000-00 or 00000000000000")
    private String cnpj;

    @Size(min = 3, message = "O nome deve ter pelo menos 5 caracteres")
    @NotBlank(message = "O nome não pode ser vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "O id do usuário não pode ser nulo")
    private UsuarioDTO usuario;

    @NotNull(message = "O id do endereço não pode ser nulo")
    private EnderecoDTO endereco;

    @NotNull(message = "O id do telefone não pode ser nulo")
    private TelefoneDTO telefone;
}
