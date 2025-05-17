package fiap.tds.dental.insurance.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_ATENDIMENTO")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atendimento")
    private Long id;

    @Size(min = 3, message = "O procedimento deve ter pelo menos 5 caracteres")
    @NotBlank(message = "O procedimento não pode ser vazio")
    @NotNull(message = "O procedimento não pode ser nulo")
    @Column(name = "tipo_procedimento", length = 100, nullable = false)
    private String tipoProcedimento;

    @Size(min = 3, message = "A descrição deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A descrição não pode ser vazio")
    @NotNull(message = "A descrição não pode ser nulo")
    @Column(name = "descricao_atendimento", length = 200, nullable = false)
    private String descricao;

    @NotNull(message = "Data de atendimento não pode ser nula")
    @Column(name = "data_atendimento", nullable = false)
    private LocalDateTime dataAtendimento;

    @NotNull(message = "O custo não pode ser vazio")
    @Positive(message = "O custo deve ser maior que zero")
    @Column(name = "custo_estimado", nullable = false)
    private BigDecimal custoEstimado;

    @OneToOne
    @JoinColumn(name = "cpf_paciente", referencedColumnName = "cpf_paciente", nullable = false)
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "cpf_dentista", referencedColumnName = "cpf_dentista", nullable = false)
    private Dentista dentista;
}
