package fiap.tds.dental.insurance.api.controller;


import fiap.tds.dental.insurance.api.dto.TelefoneDTO;
import fiap.tds.dental.insurance.api.service.TelefoneService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/telefones")
public class TelefoneController {

    @Autowired
    private final TelefoneService telefoneService;

    @GetMapping
    public String listarTelefones(Model model) {
        List<TelefoneDTO> lista = telefoneService.findAll();
        System.out.println("Lista de telefones: " + lista);
        model.addAttribute("telefones", lista);
        return "telefones/lista";
    }

    @GetMapping("/novo")
    public String novoTelefone(Model model) {
        model.addAttribute("telefone", new TelefoneDTO());
        return "telefones/formulario";
    }

    @PostMapping
    public String salvarTelefone(@Valid @ModelAttribute("telefone") TelefoneDTO telefone, BindingResult bindingResults, Model model) {
        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e -> log.info(e.toString()));
            model.addAttribute("telefone", telefone);
            return "telefones/formulario";
        }
        telefoneService.saveTelefone(telefone);
        return "redirect:/telefones";
    }

    @GetMapping("/editar/{id}")
    public String editarTelefone(@PathVariable Long id, Model model) {
        model.addAttribute("telefone", telefoneService.findById(id));
        return "telefones/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletarTelefone(@PathVariable Long id, Model model) {
        telefoneService.deleteById(id);
        return "redirect:/telefones";
    }
}
