package saude.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saude.api.model.Agendamento;
import saude.api.repository.AgendamentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public List<Agendamento> getAllAgendamentos() {
        return repository.findAll();
    }

    public Optional<Agendamento> getAgendamentoById(Long id) {
        return repository.findById(id);
    }

    // Método para verificar se já existe um agendamento com a mesma data e hora
    public boolean existeConflitoComDataHora(Agendamento agendamento) {
        Optional<Agendamento> agendamentoExistente = repository.findByDataHora(agendamento.getDataHora());
        return agendamentoExistente.isPresent();
    }

    // Método para salvar o agendamento com verificação de conflito
    public Agendamento saveAgendamento(Agendamento agendamento) {
        if (existeConflitoComDataHora(agendamento)) {
            throw new RuntimeException("Já existe um agendamento para esta data e hora.");
        }
        return repository.save(agendamento);
    }

    public void deleteAgendamento(Long id) {
        repository.deleteById(id);
    }
}
