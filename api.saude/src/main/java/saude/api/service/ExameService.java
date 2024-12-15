package saude.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saude.api.model.Exame;
import saude.api.model.Medico;
import saude.api.repository.ExameRepository;
import saude.api.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private MedicoRepository medicoRepository; // Para associar o médico ao exame

    // Retorna todos os exames
    public List<Exame> getAllExames() {
        return exameRepository.findAll();
    }

    // Retorna um exame pelo ID
    public Optional<Exame> getExameById(Long id) {
        return exameRepository.findById(id);
    }

    // Salva ou atualiza um exame (incluindo a associação com médico)
    public Exame saveOrUpdateExame(Exame exame) {
        // Se o médico não for nulo, associamos o médico ao exame
        if (exame.getMedico() != null && exame.getMedico().getId() != null) {
            Optional<Medico> medicoOptional = medicoRepository.findById(exame.getMedico().getId());
            if (medicoOptional.isPresent()) {
                exame.setMedico(medicoOptional.get());
            }
        }
        return exameRepository.save(exame);
    }

    // Deleta um exame pelo ID
    public void deleteExame(Long id) {
        exameRepository.deleteById(id);
    }
}
