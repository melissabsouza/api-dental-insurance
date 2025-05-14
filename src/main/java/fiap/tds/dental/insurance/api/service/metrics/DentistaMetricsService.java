package fiap.tds.dental.insurance.api.service.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

@Service
public class DentistaMetricsService {
    private final Timer tempoSalvarDentista;

    private final Counter contadorRegistrosDentista;

    public DentistaMetricsService(MeterRegistry registry) {
        this.contadorRegistrosDentista = registry.counter("registros_dentista_count");
        this.tempoSalvarDentista = Timer.builder("tempo.salvar.dentista")
                .description("Tempo para salvar dentista")
                .register(registry);
    }

    public Timer tempoSalvarDentista() {
        return tempoSalvarDentista;
    }


    public void contarRegistroPaciente() {
        contadorRegistrosDentista.increment();
    }
}
