package br.edu.ifpb.pweb2.caramelodasorte.service;

import br.edu.ifpb.pweb2.caramelodasorte.model.Aposta;
import br.edu.ifpb.pweb2.caramelodasorte.repository.ApostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ApostaService {

    @Autowired
    private ApostaRepository repo;


    public void preSave(Aposta aposta) {
        aposta.dezenas = Arrays.asList(new Integer[aposta.qtdeDezenas]);
        repo.save(aposta);
    }

    public void save(Aposta aposta) {
        repo.save(aposta);
    }
}
