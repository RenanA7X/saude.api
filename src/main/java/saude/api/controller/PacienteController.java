package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Paciente;
import saude.api.service.PacienteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.getAllPacientes();
    }

    @GetMapping("/{id}")
    public Optional<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.getPacienteById(id);
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.savePaciente(paciente);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
    }
<<<<<<< HEAD

    @PutMapping("/{id}")
    public Optional<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado) {
        return pacienteService.updatePaciente(id, pacienteAtualizado);
    }
=======
>>>>>>> 82b0306f282b5197eba7c38ed115a09cd5b69600
}
