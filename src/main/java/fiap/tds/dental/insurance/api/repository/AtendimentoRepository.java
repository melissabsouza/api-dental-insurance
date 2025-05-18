package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByPacienteCpf(String cpf);

}
