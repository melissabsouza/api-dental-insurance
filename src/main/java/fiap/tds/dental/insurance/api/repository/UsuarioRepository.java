package fiap.tds.dental.insurance.api.repository;


import fiap.tds.dental.insurance.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
