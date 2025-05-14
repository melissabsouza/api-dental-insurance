package fiap.tds.dental.insurance.api.service;

import fiap.tds.dental.insurance.api.dto.DentistaDTO;
import fiap.tds.dental.insurance.api.entity.*;
import fiap.tds.dental.insurance.api.repository.ClinicaRepository;
import fiap.tds.dental.insurance.api.repository.DentistaRepository;
import fiap.tds.dental.insurance.api.service.metrics.DentistaMetricsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DentistaService {
    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DentistaMetricsService dentistaMetrics;

    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;

    public DentistaDTO salvarDentista(DentistaDTO dentistaDTO) {
        return dentistaMetrics.tempoSalvarDentista().record(() -> {
            Dentista dentista;

            if (dentistaDTO.getId() == null) {
                dentista = new Dentista();

                if (dentistaRepository.existsByCpf(dentistaDTO.getCpf())) {
                    throw new RuntimeException("Já existe um dentista com esse CPF");
                }
            } else {
                dentista = dentistaRepository.findById(dentistaDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

                if (!dentista.getCpf().equals(dentistaDTO.getCpf())) {
                    throw new RuntimeException("Já existe um dentista com este CPF");
                }
            }

            dentista.setNome(dentistaDTO.getNome());
            dentista.setCpf(dentistaDTO.getCpf());
            dentista.setCro(dentistaDTO.getCro());
            dentista.setEspecialidade(dentistaDTO.getEspecialidade());
            dentista.setEmail(dentistaDTO.getEmail());
            dentista.setDataContratacao(dentistaDTO.getDataContratacao());

            if (dentistaDTO.getClinicaCnpj() != null) {
                Clinica clinica = clinicaRepository.findByCnpj(dentistaDTO.getClinicaCnpj())
                        .orElseThrow(() -> new RuntimeException("Clinica não encontrada com cnpj: " + dentistaDTO.getClinicaCnpj()));
                dentista.setClinica(clinica);
            }

            Endereco endereco = enderecoService.toEntity(dentistaDTO.getEndereco());
            Telefone telefone = telefoneService.toEntity(dentistaDTO.getTelefone());

            dentista.setEndereco(endereco);
            dentista.setTelefone(telefone);

            dentista = dentistaRepository.save(dentista);
            dentistaMetrics.contarRegistroPaciente();
            return toDto(dentista);

        });
    }

    public List<DentistaDTO> findAll() {
        List<Dentista> dentistas = dentistaRepository.findAll();
        List<DentistaDTO> dtos = dentistas.stream().map(this::toDto).toList();
        return dtos;
    }

    public void deleteById(Long id) {
        dentistaRepository.deleteById(id);
    }

    public DentistaDTO findById(Long id) {
        Optional<Dentista> byId = dentistaRepository.findById(id);
        if (byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }


    private DentistaDTO toDto(Dentista dentista) {
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setId(dentista.getId());
        dentistaDTO.setNome(dentista.getNome());
        dentistaDTO.setCpf(dentista.getCpf());
        dentistaDTO.setCro(dentista.getCro());
        dentistaDTO.setEspecialidade(dentista.getEspecialidade());
        dentistaDTO.setEmail(dentista.getEmail());
        dentistaDTO.setDataContratacao(dentista.getDataContratacao());

        dentistaDTO.setEndereco(EnderecoService.toDto(dentista.getEndereco()));
        dentistaDTO.setTelefone(telefoneService.toDto(dentista.getTelefone()));

        if (dentista.getClinica() != null) {
            dentistaDTO.setClinicaCnpj(dentista.getClinica().getCnpj());
        }

        return dentistaDTO;
    }
}
