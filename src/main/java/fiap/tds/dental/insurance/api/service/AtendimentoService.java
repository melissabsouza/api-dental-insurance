package fiap.tds.dental.insurance.api.service;

import fiap.tds.dental.insurance.api.dto.AtendimentoDTO;
import fiap.tds.dental.insurance.api.entity.Atendimento;
import fiap.tds.dental.insurance.api.entity.Dentista;
import fiap.tds.dental.insurance.api.entity.Paciente;
import fiap.tds.dental.insurance.api.repository.AtendimentoRepository;
import fiap.tds.dental.insurance.api.repository.DentistaRepository;
import fiap.tds.dental.insurance.api.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    public AtendimentoDTO salvarAtendimento(AtendimentoDTO atendimentoDTO) {
        Atendimento atendimento;

        if (atendimentoDTO.getId() == null) {
            atendimento = new Atendimento();
        } else {
            atendimento = atendimentoRepository.findById(atendimentoDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));

        }

        atendimento.setDataAtendimento(atendimentoDTO.getDataAtendimento());
        atendimento.setDescricao(atendimentoDTO.getDescricao());
        atendimento.setTipoProcedimento(atendimentoDTO.getTipoProcedimento());
        atendimento.setCustoEstimado(atendimentoDTO.getCustoEstimado());

        if (atendimentoDTO.getPacienteCpf() == null) {
            Paciente paciente = pacienteRepository.findByCpf(atendimentoDTO.getPacienteCpf())
                    .orElseThrow(() -> new RuntimeException("paciente não encontrado com cpf: " + atendimentoDTO.getPacienteCpf()));
            atendimento.setPaciente(paciente);
        }

        if (atendimentoDTO.getDentistaCpf() == null) {
            Dentista dentista = dentistaRepository.findByCpf(atendimentoDTO.getDentistaCpf())
                    .orElseThrow(() -> new RuntimeException("dentista não encontrado com cpf: " + atendimentoDTO.getDentistaCpf()));
            atendimento.setDentista(dentista);
        }

        atendimento = atendimentoRepository.save(atendimento);
        return toDto(atendimento);
    }

    public List<AtendimentoDTO> findAll() {
        List<Atendimento> atendimentos = atendimentoRepository.findAll();
        List<AtendimentoDTO> dtos = atendimentos.stream().map(this::toDto).toList();
        return dtos;
    }

    public void deleteById(Long id) {
        atendimentoRepository.deleteById(id);
    }

    public AtendimentoDTO findById(Long id) {
        Optional<Atendimento> byId = atendimentoRepository.findById(id);
        if (byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }

    private AtendimentoDTO toDto(Atendimento atendimento) {
        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();

        atendimentoDTO.setId(atendimento.getId());
        atendimentoDTO.setDataAtendimento(atendimento.getDataAtendimento());
        atendimentoDTO.setDescricao(atendimento.getDescricao());
        atendimentoDTO.setTipoProcedimento(atendimento.getTipoProcedimento());
        atendimentoDTO.setCustoEstimado(atendimento.getCustoEstimado());

        if (atendimento.getPaciente() != null) {
            atendimentoDTO.setPacienteCpf(atendimento.getPaciente().getCpf());
        }

        if (atendimento.getDentista() != null) {
            atendimentoDTO.setDentistaCpf(atendimento.getDentista().getCpf());
        }
        return atendimentoDTO;
    }
}
