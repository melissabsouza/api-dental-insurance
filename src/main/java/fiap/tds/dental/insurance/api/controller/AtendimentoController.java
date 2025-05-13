package fiap.tds.dental.insurance.api.controller;

import fiap.tds.dental.insurance.api.dto.AtendimentoDTO;
import fiap.tds.dental.insurance.api.service.AtendimentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/atendimentos")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @GetMapping
    public String listarAtendimentos(Model model) {
        List<AtendimentoDTO> lista = atendimentoService.findAll();
        model.addAttribute("atendimentos", lista);
        return "atendimentos/lista";
    }

    @GetMapping("/novo")
    public String novoAtendimento(Model model) {
        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
        model.addAttribute("atendimento", atendimentoDTO);
        return "atendimentos/formulario";
    }

    @PostMapping
    public String salvarAtendimento(@Valid @ModelAttribute("atendimento") AtendimentoDTO atendimentoDTO,
                                    BindingResult bindingResults, Model model) {
        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e -> log.info(e.toString()));
            model.addAttribute("atendimento", atendimentoDTO);
            return "atendimentos/formulario";
        }

        try {
            atendimentoService.salvarAtendimento(atendimentoDTO);
        } catch (RuntimeException e) {
            log.info("Erro: " + e.getMessage());
            model.addAttribute("erro", e.getMessage());
            return "atendimentos/formulario";
        }

        return "redirect:/atendimentos";
    }

    @GetMapping("/editar/{id}")
    public String editarAtendimento(@PathVariable Long id, Model model) {
        AtendimentoDTO atendimentoDTO = atendimentoService.findById(id);
        model.addAttribute("atendimento", atendimentoDTO);
        return "atendimentos/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletarAtendimento(@PathVariable Long id, Model model) {
        atendimentoService.deleteById(id);
        return "redirect:/atendimentos";
    }
}
