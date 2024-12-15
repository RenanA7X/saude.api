package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Paciente;
import saude.api.service.PacienteService;

@Controller
@RequestMapping("/paciente-ui")
public class PacienteUIController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.getAllPacientes());
        return "paciente/list";
    }

    @GetMapping("/novo")
    public String novoPacienteForm(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente/form";
    }

    @PostMapping
    public String salvarPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.savePaciente(paciente);
        return "redirect:/paciente-ui";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.getPacienteById(id).orElse(new Paciente());
        model.addAttribute("paciente", paciente);
        return "paciente/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
        return "redirect:/paciente-ui";
    }
}
