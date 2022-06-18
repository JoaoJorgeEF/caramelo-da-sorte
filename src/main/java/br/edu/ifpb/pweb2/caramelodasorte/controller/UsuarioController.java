package br.edu.ifpb.pweb2.caramelodasorte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class UsuarioController {
//
//    @GetMapping("/login")
//    public String login(Principal principal) {
//        if (principal != null) {
//            return "redirect:/home";
//        }
//        return "auth/login";
//    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getForm(ModelAndView modelAndView) {
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

}
