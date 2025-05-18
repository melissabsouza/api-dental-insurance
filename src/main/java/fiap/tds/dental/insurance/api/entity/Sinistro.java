package fiap.tds.dental.insurance.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "T_CHALLENGE_SINISTRO")
@Data
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinistro")
    private Long id;

    @Column(name = "descricao_sinistro", length = 1000)
    private String descricao;

    @Column(name = "resultado_sinistro", length = 10000)
    private String resultadoAnalise;
}
