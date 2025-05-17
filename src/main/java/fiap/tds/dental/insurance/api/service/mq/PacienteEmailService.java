package fiap.tds.dental.insurance.api.service.mq;


import fiap.tds.dental.insurance.api.entity.Paciente;
import fiap.tds.dental.insurance.api.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PacienteEmailService {

    private final PacienteRepository pacienteRepository;
    private final EmailProducer emailProducer;

    @Transactional
    public Paciente cadastrarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);

        String emailClinica = paciente.getClinica().getUsuario().getEmail();

        String assunto = "Novo paciente cadastrado na sua clínica!";
        String mensagem = "Novo paciente cadastrado na sua clínica:\n"
                + "Nome: " + paciente.getNome() + "\n"
                + "CPF: " + paciente.getCpf();

        EmailPayload payload = new EmailPayload();
        payload.setTo(emailClinica);
        payload.setSubject(assunto);
        payload.setBody(mensagem);

        emailProducer.sendEmail(payload);
        return paciente;
    }
}

