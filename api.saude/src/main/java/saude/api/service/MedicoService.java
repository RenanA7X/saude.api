package saude.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saude.api.model.Medico;
import saude.api.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> getMedicoById(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico saveMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }

    public Optional<Medico> updateMedico(Long id, Medico medicoAtualizado) {
        return medicoRepository.findById(id).map(medico -> {
            medico.setNome(medicoAtualizado.getNome());
            medico.setCrm(medicoAtualizado.getCrm());
            medico.setEspecialidade(medicoAtualizado.getEspecialidade());
            medico.setDataAdmissao(medicoAtualizado.getDataAdmissao());
            medico.setTelefone(medicoAtualizado.getTelefone());
            return medicoRepository.save(medico);
        });
    }
}
