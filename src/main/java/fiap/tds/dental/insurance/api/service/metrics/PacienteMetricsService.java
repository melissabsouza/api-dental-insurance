package fiap.tds.dental.insurance.api.service.metrics;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;


@Service
public class PacienteMetricsService {

    private final Timer tempoSalvarPaciente;

    private final Counter contadorRegistrosPaciente;

    public PacienteMetricsService(MeterRegistry registry) {
        this.contadorRegistrosPaciente = registry.counter("registros_paciente_count");
        this.tempoSalvarPaciente = Timer.builder("tempo.salvar.paciente")
                .description("Tempo para salvar paciente")
                .register(registry);
    }

    public Timer tempoSalvarPaciente() {
        return tempoSalvarPaciente;
    }


    public void contarRegistroPaciente() {
        contadorRegistrosPaciente.increment();
    }
}
