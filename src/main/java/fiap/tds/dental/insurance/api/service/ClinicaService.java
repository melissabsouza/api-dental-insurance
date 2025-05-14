package fiap.tds.dental.insurance.api.service;

import fiap.tds.dental.insurance.api.dto.ClinicaDTO;
import fiap.tds.dental.insurance.api.dto.EnderecoDTO;
import fiap.tds.dental.insurance.api.dto.TelefoneDTO;
import fiap.tds.dental.insurance.api.dto.UsuarioDTO;
import fiap.tds.dental.insurance.api.entity.Clinica;
import fiap.tds.dental.insurance.api.entity.Endereco;
import fiap.tds.dental.insurance.api.entity.Telefone;
import fiap.tds.dental.insurance.api.entity.Usuario;
import fiap.tds.dental.insurance.api.repository.ClinicaRepository;
import io.micrometer.core.annotation.Counted;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;
    private final UsuarioService usuarioService;

    @Counted(value = "numero.clinicas.salvas", description = "Número de vezes que uma clínica foi salva")
    public ClinicaDTO salvarClinica(ClinicaDTO clinicaDTO) {
        Clinica clinica;

        if (clinicaDTO.getId() == null) {
            clinica = new Clinica();

            if (clinicaRepository.existsByCnpj(clinicaDTO.getCnpj())) {
                throw new RuntimeException("Já existe uma clínica com esse CNPJ");
            }
        } else {
            clinica = clinicaRepository.findById(clinicaDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));

            if (!clinicaDTO.getCnpj().equals(clinica.getCnpj()) && clinicaRepository.existsByCnpj(clinicaDTO.getCnpj())) {
                throw new RuntimeException("Já existe uma clínica com esse CNPJ");
            }
        }

        clinica.setCnpj(clinicaDTO.getCnpj());
        clinica.setNome(clinicaDTO.getNome());

        Endereco endereco = enderecoService.toEntity(clinicaDTO.getEndereco());
        Telefone telefone = telefoneService.toEntity(clinicaDTO.getTelefone());
        Usuario usuario = usuarioService.toEntity(clinicaDTO.getUsuario());

        clinica.setEndereco(endereco);
        clinica.setTelefone(telefone);
        clinica.setUsuario(usuario);

        clinica = clinicaRepository.save(clinica);
        return toDto(clinica);
    }


    public ClinicaDTO editarClinica(ClinicaDTO clinicaDTO) {
        if (clinicaDTO.getNome() == null || clinicaDTO.getNome().isEmpty()) {
            throw new RuntimeException("O nome não pode ser nulo ou vazio");
        }

        Clinica clinica = clinicaRepository.findById(clinicaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));

        if (!clinicaDTO.getCnpj().equals(clinica.getCnpj())) {
            if (clinicaRepository.existsByCnpj(clinicaDTO.getCnpj())) {
                throw new RuntimeException("Já existe uma clínica com esse novo CNPJ");
            }
            clinica.setCnpj(clinicaDTO.getCnpj());
        }

        clinica.setNome(clinicaDTO.getNome());

        Endereco endereco = enderecoService.toEntity(clinicaDTO.getEndereco());
        Telefone telefone = telefoneService.toEntity(clinicaDTO.getTelefone());
        Usuario usuario = usuarioService.toEntity(clinicaDTO.getUsuario());

        clinica.setEndereco(endereco);
        clinica.setTelefone(telefone);
        clinica.setUsuario(usuario);

        clinica = clinicaRepository.save(clinica);

        return toDto(clinica);
    }

    private void attEndereco(Endereco endereco, EnderecoDTO enderecoDTO) {
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setComplemento(enderecoDTO.getComplemento());
    }

    private void attUsuario(Usuario usuario, UsuarioDTO usuarioDTO) {
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setStatus(usuarioDTO.getStatus());
    }

    private void attTelefone(Telefone telefone, TelefoneDTO telefoneDTO) {
        telefone.setTipo(telefoneDTO.getTipo());
        telefone.setNumero(telefoneDTO.getNumero());
    }

    public List<ClinicaDTO> findAll() {
        List<Clinica> list = clinicaRepository.findAll();
        List<ClinicaDTO> dtos = list.stream().map(this::toDto).toList();
        return dtos;
    }

    public void deleteById(Long id) {
        System.out.println("clinica deletada");
        clinicaRepository.deleteById(id);
    }

    public ClinicaDTO findById(Long id) {
        Optional<Clinica> byId = clinicaRepository.findById(id);
        if (byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }

    private Clinica toEntity(ClinicaDTO clinicaDTO) {
        Clinica clinica = new Clinica();
        clinica.setId(clinicaDTO.getId());
        clinica.setCnpj(clinicaDTO.getCnpj());
        clinica.setNome(clinicaDTO.getNome());

        clinica.setEndereco(enderecoService.toEntity(clinicaDTO.getEndereco()));
        clinica.setUsuario(usuarioService.toEntity(clinicaDTO.getUsuario()));
        clinica.setTelefone(telefoneService.toEntity(clinicaDTO.getTelefone()));
        return clinica;
    }

    private ClinicaDTO toDto(Clinica clinica) {
        ClinicaDTO clinicaDTO = new ClinicaDTO();
        clinicaDTO.setId(clinica.getId());
        clinicaDTO.setCnpj(clinica.getCnpj());
        clinicaDTO.setNome(clinica.getNome());

        clinicaDTO.setEndereco(EnderecoService.toDto(clinica.getEndereco()));
        clinicaDTO.setUsuario(usuarioService.toDto(clinica.getUsuario()));
        clinicaDTO.setTelefone(telefoneService.toDto(clinica.getTelefone()));

        return clinicaDTO;
    }

}
