package fiap.tds.dental.insurance.api.service.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

@Service
public class ClinicaMetricsService {
    private final Timer tempoSalvarClinica;

    private final Counter contadorRegistrosClinica;

    public ClinicaMetricsService(MeterRegistry registry) {
        this.contadorRegistrosClinica = registry.counter("registros_clinica_count");
        this.tempoSalvarClinica = Timer.builder("tempo.salvar.clinica")
                .description("Tempo para salvar clinica")
                .register(registry);
    }

    public Timer tempoSalvarClinica() {
        return tempoSalvarClinica;
    }


    public void contarRegistroClinica() {
        contadorRegistrosClinica.increment();
    }
}
