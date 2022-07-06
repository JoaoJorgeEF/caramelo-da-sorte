package br.edu.ifpb.pweb2.caramelodasorte.service;

import br.edu.ifpb.pweb2.caramelodasorte.model.Aposta;
import br.edu.ifpb.pweb2.caramelodasorte.model.Preco;
import br.edu.ifpb.pweb2.caramelodasorte.repository.ApostaRepository;
import br.edu.ifpb.pweb2.caramelodasorte.repository.SorteioRepository;
import br.edu.ifpb.pweb2.caramelodasorte.service.observer.EmailNotificationListener;
import br.edu.ifpb.pweb2.caramelodasorte.service.observer.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApostaService {

    @Autowired
    private ApostaRepository repo;

    @Autowired
    private SorteioRepository sorteioRepo;

    @Autowired
    private EventManager eventManager;

    @Autowired
    private EmailNotificationListener emailNotificationListener;

    public List<Aposta> getAll(){
        return repo.findAll();
    }

    public Aposta preSave(Aposta aposta) {
        aposta.dataDeRegistro = new Date();
        aposta.getDataDeRegistro().setHours(0);
        aposta.preco = Preco.fromQtdeDezenas(aposta.qtdeDezenas);
        aposta.dezenas = new ArrayList<Integer>();
        for (int i = 0; i < aposta.getQtdeDezenas(); i++){
            aposta.dezenas.add(0);
        }
        eventManager.subscribe("email", aposta.getApostador().getEmail(), emailNotificationListener);
        return repo.save(aposta);
    }

    public Aposta get(Long id){
        Optional<Aposta> aposta = repo.findById(id);
        if (aposta.isPresent()){
            return aposta.get();
        }
        return null;
    }

    public void save(Aposta aposta) {

        repo.save(aposta);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
