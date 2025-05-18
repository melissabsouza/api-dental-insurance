package fiap.tds.dental.insurance.api.service.ia;

import fiap.tds.dental.insurance.api.entity.Atendimento;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnaliseFraudeService {

    private final GeminiService geminiService;

    public String gerarPromptAnalise(List<Atendimento> atendimentos) {
        StringBuilder prompt = new StringBuilder("""
                Você é um sistema de detecção de fraudes odontológicas.
                Analise os atendimentos do paciente abaixo e classifique como:
                - Legítimo
                - Suspeito
                - Fraude Provável
                
                Considere:
                - Frequência de atendimentos
                - Datas próximas ou repetitivas
                - Mesmos procedimentos várias vezes
                - Custos altos ou repetitivos
                
                Responda em até 1000 caracteres, só um parágrafo curto, sem explicação longa.
                
                Histórico:
                """);

        for (Atendimento atendimento : atendimentos) {
            prompt.append(String.format("Data: %s | Procedimento: %s | Custo: R$ %.2f\n",
                    atendimento.getDataAtendimento(),
                    atendimento.getTipoProcedimento(),
                    atendimento.getCustoEstimado()));
        }

        return prompt.toString();
    }

    public String analisarFraude(List<Atendimento> atendimentos) {
        String prompt = gerarPromptAnalise(atendimentos);
        return geminiService.chamarGemini(prompt);
    }

}

