package fiap.tds.dental.insurance.api.service;

import fiap.tds.dental.insurance.api.dto.PacienteDTO;
import fiap.tds.dental.insurance.api.entity.Clinica;
import fiap.tds.dental.insurance.api.entity.Endereco;
import fiap.tds.dental.insurance.api.entity.Paciente;
import fiap.tds.dental.insurance.api.entity.Telefone;
import fiap.tds.dental.insurance.api.repository.ClinicaRepository;
import fiap.tds.dental.insurance.api.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;


    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;

    public PacienteDTO salvarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente;

        if (pacienteDTO.getId() == null) {
            paciente = new Paciente();

            if (pacienteRepository.existsByCpf(pacienteDTO.getCpf())) {
                throw new RuntimeException("Já existe um paciente com esse CPF");
            }
        } else {
            paciente = pacienteRepository.findById(pacienteDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

            if (!pacienteDTO.getCpf().equals(paciente.getCpf()) && pacienteRepository.existsByCpf(pacienteDTO.getCpf())) {
                throw new RuntimeException("Já existe um paciente com este CPF");
            }
        }

        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setNome(pacienteDTO.getNome());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setGenero(pacienteDTO.getGenero());

        if (pacienteDTO.getClinicaCnpj() != null) {
            Clinica clinica = clinicaRepository.findByCnpj(pacienteDTO.getClinicaCnpj())
                    .orElseThrow(() -> new RuntimeException("Clinica não encontrada com cnpj: " + pacienteDTO.getClinicaCnpj()));
            paciente.setClinica(clinica);
        }

        Endereco endereco = enderecoService.toEntity(pacienteDTO.getEndereco());
        Telefone telefone = telefoneService.toEntity(pacienteDTO.getTelefone());

        paciente.setEndereco(endereco);
        paciente.setTelefone(telefone);

        paciente = pacienteRepository.save(paciente);
        return toDto(paciente);
    }

    public List<PacienteDTO> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDTO> dtos = pacientes.stream().map(this::toDto).toList();
        return dtos;
    }

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

    public PacienteDTO findById(Long id) {
        Optional<Paciente> byId = pacienteRepository.findById(id);
        if (byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }

    private Paciente toEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getId());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setNome(pacienteDTO.getNome());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setGenero(pacienteDTO.getGenero());

        paciente.setEndereco(enderecoService.toEntity(pacienteDTO.getEndereco()));
        paciente.setTelefone(telefoneService.toEntity(pacienteDTO.getTelefone()));

        if (pacienteDTO.getClinicaCnpj() != null) {
            Clinica clinica = clinicaRepository.findByCnpj(pacienteDTO.getClinicaCnpj())
                    .orElseThrow(() -> new RuntimeException("Clinica não encontrada com cnpj: " + pacienteDTO.getClinicaCnpj()));
            paciente.setClinica(clinica);
        }
        return paciente;

    }

    private PacienteDTO toDto(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setCpf(paciente.getCpf());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setDataNascimento(paciente.getDataNascimento());
        pacienteDTO.setGenero(paciente.getGenero());

        pacienteDTO.setEndereco(EnderecoService.toDto(paciente.getEndereco()));
        pacienteDTO.setTelefone(telefoneService.toDto(paciente.getTelefone()));

        if (paciente.getClinica() != null) {
            pacienteDTO.setClinicaCnpj(paciente.getClinica().getCnpj());
        }

        return pacienteDTO;
    }
}
