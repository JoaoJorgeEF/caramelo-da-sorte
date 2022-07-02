package br.edu.ifpb.pweb2.caramelodasorte.controller;

import br.edu.ifpb.pweb2.caramelodasorte.model.Admin;
import br.edu.ifpb.pweb2.caramelodasorte.model.Aposta;
import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.service.ApostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

    @Autowired
    private ApostaService service;

//    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
//    public String cadastrar(@RequestBody Admin aposta){
//        return aposta.toString();
//    }

    @PostMapping("/preSave")
    public ModelAndView preSave(@Valid Aposta aposta, BindingResult validation,
                                ModelAndView mav, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.setViewName("apostas/form");
            return mav;
        }
        service.preSave(aposta);
        mav.setViewName("redirect:apostas/form");
        attrs.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
        return mav;
    }

    @PostMapping()
    public ModelAndView save(@Valid Aposta aposta, BindingResult validation,
                                ModelAndView mav, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.setViewName("apostas/form");
            return mav;
        }
        service.save(aposta);
        mav.setViewName("redirect:apostas/lista");
        attrs.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
        return mav;
    }
}
