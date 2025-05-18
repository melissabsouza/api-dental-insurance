package fiap.tds.dental.insurance.api.service.mq;

import fiap.tds.dental.insurance.api.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receberMensagem(EmailPayload mensagem) {
        System.out.println("📨 Mensagem recebida da fila: " + mensagem);

        String emailDestino = mensagem.getTo();
        String assunto = mensagem.getSubject();
        String corpo = mensagem.getBody();

        emailService.enviarEmail(emailDestino, assunto, corpo);
    }
}

