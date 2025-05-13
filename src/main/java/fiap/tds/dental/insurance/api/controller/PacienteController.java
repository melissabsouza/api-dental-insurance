package fiap.tds.dental.insurance.api.controller;

import fiap.tds.dental.insurance.api.dto.PacienteDTO;
import fiap.tds.dental.insurance.api.service.PacienteService;
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
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping
    public String listarPacientes(Model model) {
        List<PacienteDTO> lista = pacienteService.findAll();
        model.addAttribute("pacientes", lista);
        return "pacientes/lista";
    }

    @GetMapping("/novo")
    public String novoPaciente(Model model) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        model.addAttribute("paciente", pacienteDTO);
        return "pacientes/formulario";
    }

    @PostMapping
    public String salvarPaciente(@Valid @ModelAttribute("paciente") PacienteDTO pacienteDTO,
                                 BindingResult bindingResults, Model model) {
        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e -> log.info(e.toString()));
            model.addAttribute("paciente", pacienteDTO);
            return "pacientes/formulario";
        }

        try {
            pacienteService.salvarPaciente(pacienteDTO);
        } catch (RuntimeException e) {
            log.info("Erro: " + e.getMessage());
            model.addAttribute("erro", e.getMessage());
            return "pacientes/formulario";
        }

        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        PacienteDTO pacienteDTO = pacienteService.findById(id);
        model.addAttribute("paciente", pacienteDTO);
        return "pacientes/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPaciente(@PathVariable Long id, Model model) {
        pacienteService.deleteById(id);
        return "redirect:/pacientes";
    }
}
