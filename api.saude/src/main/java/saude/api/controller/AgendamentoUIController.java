package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Agendamento;
import saude.api.service.AgendamentoService;
import saude.api.service.MedicoService;
import saude.api.service.PacienteService;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/agendamento-ui")
public class AgendamentoUIController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    // Lista todos os agendamentos
    @GetMapping
    public String listAgendamentos(Model model) {
        var agendamentos = agendamentoService.getAllAgendamentos();

        // Define o formato desejado para a data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Formata a data de cada agendamento
        for (Agendamento agendamento : agendamentos) {
            String formattedDate = agendamento.getDataHora().format(formatter);
            agendamento.setDataHoraFormatada(formattedDate);
        }

        model.addAttribute("agendamentos", agendamentos);
        return "agendamento/list";
    }

    // Página de criação de novo agendamento
    @GetMapping("/novo")
    public String novoAgendamentoForm(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        model.addAttribute("medicos", medicoService.getAllMedicos());
        model.addAttribute("pacientes", pacienteService.getAllPacientes());
        return "agendamento/form";
    }

    // Salva ou atualiza um agendamento
    @PostMapping
    public String salvarAgendamento(@ModelAttribute Agendamento agendamento) {
        try {
            agendamentoService.saveAgendamento(agendamento);
            return "redirect:/agendamento-ui";
        } catch (RuntimeException e) {
            return "redirect:/error"; 
        }
    }

    // Página de edição de um agendamento
    @GetMapping("/editar/{id}")
    public String editarAgendamento(@PathVariable Long id, Model model) {
        Agendamento agendamento = agendamentoService.getAgendamentoById(id).orElse(new Agendamento());
        model.addAttribute("agendamento", agendamento);
        model.addAttribute("medicos", medicoService.getAllMedicos());
        model.addAttribute("pacientes", pacienteService.getAllPacientes());
        return "agendamento/form";
    }

    // Exclui um agendamento
    @GetMapping("/excluir/{id}")
    public String excluirAgendamento(@PathVariable Long id) {
        agendamentoService.deleteAgendamento(id);
        return "redirect:/agendamento-ui";
    }
}
