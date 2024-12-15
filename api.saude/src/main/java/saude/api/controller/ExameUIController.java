package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Exame;
import saude.api.service.ExameService;
import saude.api.service.PacienteService;
import saude.api.service.MedicoService; // Importando o serviço do médico

@Controller
@RequestMapping("/exame-ui")
public class ExameUIController {

    @Autowired
    private ExameService exameService;

    @Autowired
    private PacienteService pacienteService; // Para preencher a lista de pacientes no formulário

    @Autowired
    private MedicoService medicoService; // Para preencher a lista de médicos no formulário

    // Lista todos os exames
    @GetMapping
    public String listExames(Model model) {
        model.addAttribute("exames", exameService.getAllExames());
        return "exame/list";  // Retorna o template de listagem de exames
    }

    // Formulário para adicionar um novo exame
    @GetMapping("/novo")
    public String novoExameForm(Model model) {
        model.addAttribute("exame", new Exame());
        model.addAttribute("pacientes", pacienteService.getAllPacientes());  // Adiciona a lista de pacientes ao modelo
        model.addAttribute("medicos", medicoService.getAllMedicos());  // Adiciona a lista de médicos ao modelo
        return "exame/form";  // Retorna o template do formulário de criação
    }

    // Salva ou atualiza um exame
    @PostMapping
    public String salvarExame(@ModelAttribute Exame exame) {
        exameService.saveOrUpdateExame(exame);  // Chama o serviço para salvar o exame
        return "redirect:/exame-ui";  // Redireciona para a lista de exames
    }

    // Formulário para editar um exame existente
    @GetMapping("/editar/{id}")
    public String editarExame(@PathVariable Long id, Model model) {
        Exame exame = exameService.getExameById(id).orElse(new Exame());  // Busca o exame por ID, caso não exista, cria um novo
        model.addAttribute("exame", exame);
        model.addAttribute("pacientes", pacienteService.getAllPacientes());  // Adiciona a lista de pacientes ao modelo
        model.addAttribute("medicos", medicoService.getAllMedicos());  // Adiciona a lista de médicos ao modelo
        return "exame/form";  // Retorna o template do formulário de edição
    }

    // Exclui um exame pelo ID
    @GetMapping("/excluir/{id}")
    public String excluirExame(@PathVariable Long id) {
        exameService.deleteExame(id);  // Chama o serviço para deletar o exame
        return "redirect:/exame-ui";  // Redireciona para a lista de exames
    }
}
