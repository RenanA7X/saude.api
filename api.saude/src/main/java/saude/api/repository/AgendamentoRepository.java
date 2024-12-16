package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saude.api.model.Agendamento;
import java.time.LocalDateTime;
import java.util.Optional;


public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByDataHora(LocalDateTime dataHora);
}