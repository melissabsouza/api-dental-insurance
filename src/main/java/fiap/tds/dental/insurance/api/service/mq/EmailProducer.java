package fiap.tds.dental.insurance.api.service.mq;

import fiap.tds.dental.insurance.api.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue.email}")
    private String emailQueue;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmail(Object payload) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, payload);

    }
}
