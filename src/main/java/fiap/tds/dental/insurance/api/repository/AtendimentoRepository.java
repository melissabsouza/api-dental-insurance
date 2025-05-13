package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
