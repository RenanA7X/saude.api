package saude.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saude.api.model.Paciente;
import saude.api.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> updatePaciente(Long id, Paciente pacienteAtualizado) {
        return pacienteRepository.findById(id).map(paciente -> {
            // Atualiza os campos permitidos
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setDataNascimento(pacienteAtualizado.getDataNascimento());
            paciente.setCpf(pacienteAtualizado.getCpf());
            paciente.setTelefone(pacienteAtualizado.getTelefone());
            // Salva as alterações no banco de dados
            return pacienteRepository.save(paciente);
        });
    }
}
