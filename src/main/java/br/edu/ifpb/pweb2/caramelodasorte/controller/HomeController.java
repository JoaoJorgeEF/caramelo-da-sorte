package br.edu.ifpb.pweb2.caramelodasorte.controller;

import br.edu.ifpb.pweb2.caramelodasorte.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping({"/","/home"})
    public String showHomePage(Model mav) {
        mav.addAttribute("menu", "home");
        return "home";
    }
}
