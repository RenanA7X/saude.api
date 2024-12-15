package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saude.api.model.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {
    // Métodos personalizados podem ser adicionados aqui, caso necessário
}
