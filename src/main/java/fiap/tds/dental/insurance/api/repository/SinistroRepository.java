package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Sinistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinistroRepository extends JpaRepository<Sinistro, Long> {}

