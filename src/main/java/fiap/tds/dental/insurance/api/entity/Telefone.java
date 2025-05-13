package fiap.tds.dental.insurance.api.entity;

import fiap.tds.dental.insurance.api.enums.TipoTelefone;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_TELEFONE")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefone")
    private Long id;

    @Pattern(regexp = "\\d{2}\\s?(9\\d{4}-?\\d{4}|\\d{4}-?\\d{4})",
            message = "Use XX 9XXXX-XXXX, XX 9XXXX XXXX ou XX 9XXXXXXXX")
    @Column(name = "numero_telefone", length = 15, nullable = false)
    private String numero;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo de telefone não pode ser nulo")
    @Column(name = "tipo_telefone", nullable = false)
    private TipoTelefone tipo;
}
