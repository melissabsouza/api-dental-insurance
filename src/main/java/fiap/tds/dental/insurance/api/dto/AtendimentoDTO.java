package fiap.tds.dental.insurance.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AtendimentoDTO {
    private Long id;

    @Size(min = 3, message = "O procedimento deve ter pelo menos 5 caracteres")
    @NotBlank(message = "O procedimento não pode ser vazio")
    @NotNull(message = "O procedimento não pode ser nulo")
    private String tipoProcedimento;

    @Size(min = 3, message = "A descrição deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A descrição não pode ser vazio")
    @NotNull(message = "A descrição não pode ser nulo")
    private String descricao;

    @NotNull(message = "Data de atendimento não pode ser nula")
    private LocalDateTime dataAtendimento;

    @NotNull(message = "O custo não pode ser vazio")
    @Positive(message = "O custo deve ser maior que zero")
    private BigDecimal custoEstimado;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message = "Formato de CPF inválido, use 12345678900")
    @Valid
    private String pacienteCpf;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message = "Formato de CPF inválido, use 12345678900")
    @Valid
    private String dentistaCpf;

}
