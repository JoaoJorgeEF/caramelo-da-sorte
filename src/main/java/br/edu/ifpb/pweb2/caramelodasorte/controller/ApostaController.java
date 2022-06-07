package br.edu.ifpb.pweb2.caramelodasorte.controller;

import br.edu.ifpb.pweb2.caramelodasorte.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String cadastrar(@RequestBody Admin aposta){
        return aposta.toString();
    }
}
