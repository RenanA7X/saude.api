package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saude.api.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Aqui você pode criar métodos personalizados de consulta, se necessário.
}
