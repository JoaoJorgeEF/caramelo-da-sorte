package br.edu.ifpb.pweb2.caramelodasorte.controller;

import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.service.observer.EmailNotificationListener;
import br.edu.ifpb.pweb2.caramelodasorte.service.SorteioService;
import br.edu.ifpb.pweb2.caramelodasorte.service.proxy.ImpSorteioProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sorteios")
public class SorteioController {

    @Autowired
    private SorteioService service;

    @Autowired
    private ImpSorteioProxy impSorteioProxy;

    @Autowired
    private EmailNotificationListener emailNotificationListener;

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
        mav.setViewName("sorteios/realizar-sorteio");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Sorteio sorteio, BindingResult validation,
                             ModelAndView mav, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.setViewName("sorteios/form");
            return mav;
        }
        if (sorteio.getId() != null){
            service.saveDezenas(sorteio);
            service.checkWinner(sorteio.getId());
        } else{
            service.save(sorteio);
        }
        impSorteioProxy.refreshCache();
        mav.setViewName("redirect:sorteios/list");
        attrs.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
        return mav;
    }

    @RequestMapping(value = "/{id}/gerar-dezenas")
    public ModelAndView saveDezenas(@PathVariable(value = "id") Long id, ModelAndView mav) {
        Sorteio sorteio = service.get(id);
//        ArrayList<Integer> dezenas = new ArrayList<>();
        List<Integer> dezenasSorteadas = Arrays.stream(new Random().ints(1,60).distinct().limit(6).toArray()).boxed().collect(Collectors.toList()) ;
//        for (int dezena: sorteio.getDezenasSorteadas()) {
////            dezenas.add(new Random().nextInt(60) + 1);
//        }
        sorteio.setDezenasSorteadas(dezenasSorteadas);

        service.saveDezenas(sorteio);
        service.checkWinner(id);

        mav.addObject("sorteios", service.getAllWithFutureDate());
        mav.addObject("menu", "sorteios");
        mav.setViewName("sorteios/list");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView listAll(ModelAndView mav) {
        mav.addObject("sorteios", service.getAllWithFutureDate());
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
            mav.addObject("sorteios", service.getAllWithFutureDate());
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
