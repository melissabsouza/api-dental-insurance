package fiap.tds.dental.insurance.api.controller;

import fiap.tds.dental.insurance.api.entity.Atendimento;
import fiap.tds.dental.insurance.api.repository.AtendimentoRepository;
import fiap.tds.dental.insurance.api.service.ia.AnaliseFraudeService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/sinistros")
public class SinistrosController {

    private final AtendimentoRepository atendimentoRepository;
    private final AnaliseFraudeService analiseFraudeService;

    @GetMapping
    public String mostrarFormulario(Model model) {
        return "sinistros";
    }


    @PostMapping
    public String analisarHistorico(@RequestParam("cpfPaciente") String cpf, Model model) {
        List<Atendimento> atendimentos = atendimentoRepository.findByPacienteCpf(cpf);

        if (atendimentos.isEmpty()) {
            model.addAttribute("mensagem", "Nenhum atendimento encontrado para esse CPF.");
            return "sinistros";
        }

        String resultadoIA = analiseFraudeService.analisarFraude(atendimentos);

        model.addAttribute("resultado", resultadoIA);
        model.addAttribute("cpf", cpf);
        return "sinistros";
    }

}

