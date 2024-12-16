package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Agendamento;
import saude.api.service.AgendamentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @GetMapping
    public List<Agendamento> getAllAgendamentos() {
        return service.getAllAgendamentos();
    }

    @GetMapping("/{id}")
    public Optional<Agendamento> getAgendamentoById(@PathVariable Long id) {
        return service.getAgendamentoById(id);
    }

    @PostMapping
    public Agendamento createAgendamento(@RequestBody Agendamento agendamento) {
        return service.saveAgendamento(agendamento);
    }

    @DeleteMapping("/{id}")
    public void deleteAgendamento(@PathVariable Long id) {
        service.deleteAgendamento(id);
    }

    @PutMapping("/{id}")
    public Optional<Agendamento> updateAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamentoAtualizado) {
        Optional<Agendamento> agendamentoExistente = service.getAgendamentoById(id);
        if (agendamentoExistente.isPresent()) {
            Agendamento agendamento = agendamentoExistente.get();
            agendamento.setMedico(agendamentoAtualizado.getMedico());
            agendamento.setPaciente(agendamentoAtualizado.getPaciente());
            agendamento.setDataHora(agendamentoAtualizado.getDataHora());
            agendamento.setStatus(agendamentoAtualizado.getStatus());
            return Optional.of(service.saveAgendamento(agendamento));
        }
        return Optional.empty();
    }
}