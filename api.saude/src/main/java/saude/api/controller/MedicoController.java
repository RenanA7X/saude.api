package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Medico;
import saude.api.service.MedicoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> getAllMedicos() {
        return medicoService.getAllMedicos();
    }

    @GetMapping("/{id}")
    public Optional<Medico> getMedicoById(@PathVariable Long id) {
        return medicoService.getMedicoById(id);
    }

    @PostMapping
    public Medico createMedico(@RequestBody Medico medico) {
        return medicoService.saveMedico(medico);
    }

    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
    }

    @PutMapping("/{id}")
    public Optional<Medico> updateMedico(@PathVariable Long id, @RequestBody Medico medicoAtualizado) {
        return medicoService.updateMedico(id, medicoAtualizado);
    }
}
