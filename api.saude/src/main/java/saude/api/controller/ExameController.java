package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Exame;
import saude.api.model.Medico;
import saude.api.service.ExameService;
import saude.api.service.MedicoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exames")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @Autowired
    private MedicoService medicoService;  // Para lidar com os médicos

    // Retorna todos os exames
    @GetMapping
    public List<Exame> getAllExames() {
        return exameService.getAllExames();
    }

    // Retorna um exame pelo ID
    @GetMapping("/{id}")
    public Optional<Exame> getExameById(@PathVariable Long id) {
        return exameService.getExameById(id);
    }

    // Cria ou atualiza um exame
    @PostMapping
    public Exame createOrUpdateExame(@RequestBody Exame exame) {
        return exameService.saveOrUpdateExame(exame);
    }

    // Deleta um exame pelo ID
    @DeleteMapping("/{id}")
    public void deleteExame(@PathVariable Long id) {
        exameService.deleteExame(id);
    }
}