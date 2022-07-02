package br.edu.ifpb.pweb2.caramelodasorte.controller;

import br.edu.ifpb.pweb2.caramelodasorte.model.Usuario;
import br.edu.ifpb.pweb2.caramelodasorte.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @RequestMapping(method = RequestMethod.GET)
    //@PreAuthorize("hasRole('ADMIN')")
    public ModelAndView getForm(ModelAndView modelAndView) {
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String cadastrar(@ModelAttribute("Usuario") Usuario usuario, Model model){
        service.cadastrar(usuario);
        return "home";
    }
}
