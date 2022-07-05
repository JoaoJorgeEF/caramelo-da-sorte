package br.edu.ifpb.pweb2.caramelodasorte.controller;

import br.edu.ifpb.pweb2.caramelodasorte.model.Aposta;
import br.edu.ifpb.pweb2.caramelodasorte.model.Apostador;
import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.service.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sorteios")
public class SorteioController {

    @Autowired
    private SorteioService service;

    @RequestMapping("/form")
    public ModelAndView getForm(Sorteio sorteio, ModelAndView mav) {
        mav.addObject("sorteio", sorteio);
        mav.setViewName("sorteios/form");
        return mav;
    }

    @RequestMapping("/{id}/realizar-sorteio")
    public ModelAndView getFormSortear(@PathVariable(value = "id") Long id, ModelAndView mav) {
        Sorteio sorteio = service.get(id);

        mav.addObject("sorteioRealizar", sorteio);
        mav.setViewName("sorteio/{id}/realizar-sorteio");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Sorteio sorteio, BindingResult validation,
                             ModelAndView mav, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.setViewName("sorteios/form");
            return mav;
        }
        service.save(sorteio);
        mav.setViewName("redirect:sorteios/list");
        attrs.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView listAll(ModelAndView mav) {
        mav.addObject("sorteios", service.getAll());
        mav.addObject("menu", "sorteios");
        mav.setViewName("sorteios/list");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getSorteioById(@PathVariable(value = "id") Long id, ModelAndView mav) {
        Sorteio sorteio = service.get(id);
        if (sorteio != null) {
            mav.addObject("sorteio", sorteio);
            mav.setViewName("sorteios/form");
        } else {
            mav.addObject("sorteios", service.getAll());
            mav.addObject("menu", "sorteios");
            mav.addObject("mensagem", "Sorteio com id=" + id + " não encontrado!");
            mav.setViewName("sorteios/list");
        }
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable("id") Long id, ModelAndView mav, RedirectAttributes attr) {
        service.delete(id);
        attr.addFlashAttribute("mensagem", "Sorteio removido com sucesso!");
        mav.setViewName("redirect:/sorteios/list");
        return mav;
    }
}
