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
import java.util.Map;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

    @Autowired
    private ApostaService service;

    @RequestMapping("/form")
    public ModelAndView getForm(Aposta aposta, ModelAndView mav) {
        mav.addObject("aposta", aposta);
        mav.setViewName("apostas/form");
        return mav;
    }

    @RequestMapping("/form-dezenas")
    public ModelAndView getFormDezenas(Aposta aposta, ModelAndView mav) {
        System.out.println(aposta);
        mav.addObject("aposta", aposta);
        mav.setViewName("apostas/form-dezenas");
        return mav;
    }

    @PostMapping()
    public ModelAndView save(@Valid Aposta aposta, BindingResult validation,
                                ModelAndView mav, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.setViewName("apostas/form");
            return mav;
        }
        if (aposta.getDezenas().isEmpty()){
            aposta = service.preSave(aposta);
            mav.addObject("aposta", aposta);
            mav.setViewName("redirect:apostas/form-dezenas");
            attrs.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
            return mav;
        }

        service.save(aposta);
        mav.setViewName("redirect:apostas/list");
        attrs.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView listAll(ModelAndView mav) {
        mav.addObject("apostas", service.getAll());
        mav.addObject("menu", "apostas");
        mav.setViewName("apostas/list");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable("id") Long id, ModelAndView mav, RedirectAttributes attr) {
        service.delete(id);
        attr.addFlashAttribute("mensagem", "Sorteio removido com sucesso!");
        mav.setViewName("redirect:/apostas/list");
        return mav;
    }

}
