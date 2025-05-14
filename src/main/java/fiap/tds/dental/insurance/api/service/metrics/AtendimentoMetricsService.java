package fiap.tds.dental.insurance.api.service.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoMetricsService {
    private final Timer tempoSalvarAtendimento;

    private final Counter contadorRegistrosAtendimento;

    public AtendimentoMetricsService(MeterRegistry registry) {
        this.contadorRegistrosAtendimento = registry.counter("registros_atendimento_count");
        this.tempoSalvarAtendimento = Timer.builder("tempo.salvar.atendimento")
                .description("Tempo para salvar atendimento")
                .register(registry);
    }

    public Timer tempoSalvarAtendimento() {
        return tempoSalvarAtendimento;
    }


    public void contarRegistroAtendimento() {
        contadorRegistrosAtendimento.increment();
    }
}
