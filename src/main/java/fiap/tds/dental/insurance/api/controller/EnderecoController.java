package fiap.tds.dental.insurance.api.controller;


import fiap.tds.dental.insurance.api.dto.EnderecoDTO;
import fiap.tds.dental.insurance.api.service.EnderecoService;
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
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private final EnderecoService enderecoService;

    @GetMapping
    public String listarEnderecos(Model model) {
        List<EnderecoDTO> lista = enderecoService.findAll();
        System.out.println("Lista de enderecos: " + lista);
        model.addAttribute("enderecos", lista);
        return "enderecos/lista";
    }

    @GetMapping("/novo")
    public String novoEndereco(Model model) {
        model.addAttribute("endereco", new EnderecoDTO());
        return "enderecos/formulario";
    }

    @PostMapping
    public String salvarEndereco(@Valid @ModelAttribute("endereco") EnderecoDTO endereco, BindingResult bindingResults, Model model) {
        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e -> log.info(e.toString()));
            model.addAttribute("endereco", endereco);
            return "enderecos/formulario";
        }
        enderecoService.salvarEndereco(endereco);
        return "redirect:/enderecos";
    }

    @GetMapping("/editar/{id}")
    public String editarEndereco(@PathVariable Long id, Model model) {
        model.addAttribute("endereco", enderecoService.findById(id));
        return "enderecos/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletarEndereco(@PathVariable Long id, Model model) {
        enderecoService.deleteById(id);
        return "redirect:/enderecos";
    }
}
