package fiap.tds.dental.insurance.api.controller;

import fiap.tds.dental.insurance.api.dto.DentistaDTO;
import fiap.tds.dental.insurance.api.dto.EnderecoDTO;
import fiap.tds.dental.insurance.api.dto.PacienteDTO;
import fiap.tds.dental.insurance.api.dto.TelefoneDTO;
import fiap.tds.dental.insurance.api.service.DentistaService;
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
@RequestMapping("/dentistas")
public class DentistaController {
    private final DentistaService dentistaService;


    @GetMapping
    public String listarDentistas(Model model) {
        List<DentistaDTO> lista = dentistaService.findAll();
        System.out.println("Lista de Dentistas: " + lista);
        model.addAttribute("dentistas", lista);
        return "dentistas/lista";
    }

    @GetMapping("/novo")
    public String novoDentista(Model model) {
        DentistaDTO dentistaDTO = new DentistaDTO();
        model.addAttribute("dentista", dentistaDTO);
        return "dentistas/formulario";
    }

    @PostMapping
    public String salvarDentista(@Valid @ModelAttribute("dentista") DentistaDTO dentistaDTO,
                                 BindingResult bindingResults, Model model) {
        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e -> log.info(e.toString()));
            model.addAttribute("dentista", dentistaDTO);
            return "dentistas/formulario";
        }

        try {
            dentistaService.salvarDentista(dentistaDTO);
        } catch (RuntimeException e) {
            log.info("Erro: " + e.getMessage());
            model.addAttribute("erro", e.getMessage());
            return "dentistas/formulario";
        }

        return "redirect:/dentistas";
    }

    @GetMapping("/editar/{id}")
    public String editarDentista(@PathVariable Long id, Model model) {
        DentistaDTO dentistaDTO = dentistaService.findById(id);
        model.addAttribute("dentista", dentistaDTO);
        return "dentistas/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletarDentista(@PathVariable Long id, Model model) {
        dentistaService.deleteById(id);
        return "redirect:/dentistas";
    }
}
