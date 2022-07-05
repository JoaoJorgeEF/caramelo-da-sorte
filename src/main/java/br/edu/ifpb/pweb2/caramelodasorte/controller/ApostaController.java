package br.edu.ifpb.pweb2.caramelodasorte.controller;

import br.edu.ifpb.pweb2.caramelodasorte.model.*;
import br.edu.ifpb.pweb2.caramelodasorte.repository.ApostadorRepository;
import br.edu.ifpb.pweb2.caramelodasorte.repository.SorteioRepository;
import br.edu.ifpb.pweb2.caramelodasorte.service.ApostaService;
import br.edu.ifpb.pweb2.caramelodasorte.service.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

    @Autowired
    private ApostaService service;

    @Autowired
    private SorteioService sorteioService;

    @Autowired
    private ApostadorRepository apostadorRepository;

    @RequestMapping("/form")
    public ModelAndView getForm(Aposta aposta, ModelAndView mav) {
        mav.addObject("aposta", aposta);
        mav.setViewName("apostas/form");
        return mav;
    }

    @RequestMapping("/form-dezenas")
    public ModelAndView getFormDezenas(Aposta aposta, ModelAndView mav) {
        System.out.println(aposta);
        mav.addObject("apostaDezena", aposta);
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
            Apostador apostador = this.getCurrentApostador();
            aposta.setApostador(apostador);
            Aposta ret = service.preSave(aposta);
            mav.addObject("apostaDezena", service.get(aposta.getId()));
            mav.setViewName("apostas/form-dezenas");
            attrs.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
            return mav;
        }

        service.save(aposta);
        mav.setViewName("redirect:apostas/list");
        attrs.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
        return mav;
    }

    @RequestMapping(value = "/{id}/save-favorita")
    public ModelAndView saveFavorita(@PathVariable(value = "id") Long id, ModelAndView mav) {
        Aposta foundAposta = service.get(id);
        foundAposta.setFavorita(true);

        service.save(foundAposta);

        mav.addObject("apostas", service.getAll().stream()
                .filter(a -> a.getApostador() != null && this.getCurrentApostador() != null &&
                        a.getApostador().getId() == this.getCurrentApostador().getId()).collect(Collectors.toList()));
        mav.addObject("menu", "apostas");
        mav.setViewName("apostas/list");
        return mav;
    }

    @RequestMapping(value = "/{id}/remove-favorita")
    public ModelAndView removeFavorita(@PathVariable(value = "id") Long id, ModelAndView mav) {
        Aposta foundAposta = service.get(id);
        foundAposta.setFavorita(false);

        service.save(foundAposta);

        mav.addObject("apostasFavoritas", service.getAll().stream().filter(f -> f.isFavorita == true &&
                f.getApostador() != null && this.getCurrentApostador() != null &&
                f.getApostador().getId() == this.getCurrentApostador().getId()).collect(Collectors.toList()));
        mav.addObject("menu", "apostasFavoritas");
        mav.setViewName("apostas/list-favoritas");
        return mav;
    }

    @RequestMapping(value = "/{id}/reaproveitar-favorita")
    public ModelAndView reaproveitarFavorita(@PathVariable(value = "id") Long id, ModelAndView mav) {
        Aposta foundAposta = service.get(id);

        Aposta aposta = new Aposta();
        aposta.setDezenas(new ArrayList<Integer>(foundAposta.getDezenas()));
        aposta.setPreco(foundAposta.getPreco());
        aposta.setApostador(foundAposta.getApostador());
        aposta.setQtdeDezenas(foundAposta.getQtdeDezenas());
        aposta.setSorteio(foundAposta.getSorteio());

        service.save(aposta);

        mav.addObject("apostas", service.getAll().stream()
                .filter(a -> a.getApostador() != null && this.getCurrentApostador() != null &&
                        a.getApostador().getId() == this.getCurrentApostador().getId()).collect(Collectors.toList()));
        mav.addObject("menu", "apostas");
        mav.setViewName("apostas/list");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView listAll(ModelAndView mav) {
        mav.addObject("apostas", service.getAll().stream()
                .filter(a -> a.getApostador() != null && this.getCurrentApostador() != null &&
                        a.getApostador().getId() == this.getCurrentApostador().getId()).collect(Collectors.toList()));
        mav.addObject("menu", "apostas");
        mav.setViewName("apostas/list");
        return mav;
    }

    @GetMapping("/list-favoritas")
    public ModelAndView listFavoritas(ModelAndView mav) {
        mav.addObject("apostasFavoritas", service.getAll().stream().filter(f -> f.isFavorita == true &&
                f.getApostador() != null && this.getCurrentApostador() != null &&
                f.getApostador().getId() == this.getCurrentApostador().getId()).collect(Collectors.toList()));
        mav.addObject("menu", "apostasFavoritas");
        mav.setViewName("apostas/list-favoritas");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable("id") Long id, ModelAndView mav, RedirectAttributes attr) {
        service.delete(id);
        attr.addFlashAttribute("mensagem", "Sorteio removido com sucesso!");
        mav.setViewName("redirect:/apostas/list");
        return mav;
    }

    @ModelAttribute("sorteios")
    public List<Sorteio> getSorteiosOptions() {
        return sorteioService.getAllWithFutureDate(new Date(System.currentTimeMillis()));
    }

    private Apostador getCurrentApostador(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Apostador apostador = apostadorRepository.findByUserUsername(name);
        return apostador;
    }
}
