package fiap.tds.dental.insurance.api.dto;


import fiap.tds.dental.insurance.api.enums.StatusUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;

    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotNull(message = "senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser vazia")
    @Size(min = 5, message = "A senha deve ter pelo menos 5 caracteres")
    private String senha;


    private StatusUsuario status;
}
