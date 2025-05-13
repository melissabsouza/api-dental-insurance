package fiap.tds.dental.insurance.api.controller;


import fiap.tds.dental.insurance.api.dto.UsuarioDTO;
import fiap.tds.dental.insurance.api.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<UsuarioDTO> lista = usuarioService.findAll();
        System.out.println("Lista de usuarios: " + lista);
        model.addAttribute("usuarios", lista);
        return "usuarios/lista";

    }

    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuarios/formulario";

    }

    @PostMapping
    public String salvarUsuario(@Valid @ModelAttribute("usuario") UsuarioDTO usuario, BindingResult bindingResults, Model model) {
        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e -> log.info(e.toString()));
            model.addAttribute("usuario", usuario);
            return "usuarios/formulario";
        }
        usuarioService.saveUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.findById(id));
        return "usuarios/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable Long id, Model model) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios";
    }
}
