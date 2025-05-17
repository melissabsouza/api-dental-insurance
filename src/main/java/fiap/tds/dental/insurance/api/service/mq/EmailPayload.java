package fiap.tds.dental.insurance.api.service.mq;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailPayload implements Serializable {
    private String to;
    private String subject;
    private String body;
}

