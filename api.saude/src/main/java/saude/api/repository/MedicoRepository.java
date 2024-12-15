package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saude.api.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Podemos adicionar métodos personalizados aqui, caso necessário.
}
