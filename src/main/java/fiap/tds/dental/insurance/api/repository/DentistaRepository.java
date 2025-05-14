package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Dentista;
import fiap.tds.dental.insurance.api.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByCro(String cro);
    boolean existsByEmail(String email);

    Optional<Dentista> findByCpf(String cpf);

}
