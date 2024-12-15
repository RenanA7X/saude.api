package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Medico;
import saude.api.service.MedicoService;

@Controller
@RequestMapping("/medico-ui")
public class MedicoUIController {

    @Autowired
    private MedicoService medicoService;

    // Lista todos os médicos
    @GetMapping
    public String listMedicos(Model model) {
        model.addAttribute("medicos", medicoService.getAllMedicos());
        return "medico/list";
    }

    // Página de criação de novo médico
    @GetMapping("/novo")
    public String novoMedicoForm(Model model) {
        model.addAttribute("medico", new Medico());
        return "medico/form";
    }

    // Salva ou atualiza um médico
    @PostMapping
    public String salvarMedico(@ModelAttribute Medico medico) {
        medicoService.saveMedico(medico);
        return "redirect:/medico-ui";
    }

    // Página de edição de um médico
    @GetMapping("/editar/{id}")
    public String editarMedico(@PathVariable Long id, Model model) {
        Medico medico = medicoService.getMedicoById(id).orElse(new Medico());
        model.addAttribute("medico", medico);
        return "medico/form";
    }

    // Exclui um médico
    @GetMapping("/excluir/{id}")
    public String excluirMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
        return "redirect:/medico-ui";
    }
}
