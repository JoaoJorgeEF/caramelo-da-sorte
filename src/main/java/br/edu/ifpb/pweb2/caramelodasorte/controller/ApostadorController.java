package br.edu.ifpb.pweb2.caramelodasorte.controller;

import br.edu.ifpb.pweb2.caramelodasorte.model.Apostador;
import br.edu.ifpb.pweb2.caramelodasorte.repository.ApostadorRepository;
import br.edu.ifpb.pweb2.caramelodasorte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/apostadores")
public class ApostadorController {

    @Autowired
    private ApostadorRepository apostadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping("/form")
    public ModelAndView getForm(Apostador apostador, ModelAndView mav) {
        mav.addObject("apostador", apostador);
        mav.setViewName("apostadores/form");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(Apostador apostador, BindingResult validation,
                             ModelAndView mav, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.setViewName("apostadores/form");
            return mav;
        }
        usuarioRepository.save(apostador.getUser());
        apostadorRepository.save(apostador);
        mav.setViewName("redirect:apostadores/list");
        attrs.addFlashAttribute("mensagem", "Apostador cadastrado com sucesso!");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView listAll(ModelAndView mav) {
        List<Apostador> apostadores = apostadorRepository.findAll();
        mav.addObject("apostadores", apostadorRepository.findAll());
        mav.addObject("menu", "apostadores");
        mav.setViewName("apostadores/list");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getCorrentistaById(@PathVariable(value = "id") Long id, ModelAndView mav) {
        Apostador apostador = null;
        Optional<Apostador> opApostador = apostadorRepository.findById(id);
        if (opApostador.isPresent()) {
            apostador = opApostador.get();
            mav.addObject("apostador", apostador);
            mav.setViewName("apostadores/form");
        } else {
            mav.addObject("mensagem", "Apostador com id=" + id + " não encontrado!");
//            mav.setViewName("contas/list");
            mav.setViewName("apostadores/list");
        }
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable("id") Long id, ModelAndView mav, RedirectAttributes attr) {
        System.out.println("sdklfjsdlkfjsdlkjlsdkfjlsdkjfklsdjflk;dsj");
        apostadorRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Correntista removido com sucesso!");
        mav.setViewName("redirect:/apostadores/list");
        return mav;
    }

//    @ModelAttribute("users")
//    public List<User> getUserOptions() {
//        return userRepository.findByEnabledTrue();
//    }

}
