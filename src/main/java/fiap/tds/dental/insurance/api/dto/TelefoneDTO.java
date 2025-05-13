package fiap.tds.dental.insurance.api.dto;


import fiap.tds.dental.insurance.api.enums.TipoTelefone;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TelefoneDTO {

    private Long id;

    @Pattern(regexp = "\\d{2}\\s?(9\\d{4}-?\\d{4}|\\d{4}-?\\d{4})",
            message = "Use XX 9XXXX-XXXX, XX 9XXXX XXXX ou XX 9XXXXXXXX")
    private String numero;

    @NotNull(message = "O tipo de telefone não pode ser nulo")
    private TipoTelefone tipo;
}
