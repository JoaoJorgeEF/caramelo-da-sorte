package br.edu.ifpb.pweb2.caramelodasorte.service;

import br.edu.ifpb.pweb2.caramelodasorte.model.Aposta;
import br.edu.ifpb.pweb2.caramelodasorte.model.Preco;
import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.repository.ApostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ApostaService {

    @Autowired
    private ApostaRepository repo;

    public List<Aposta> getAll(){
        return repo.findAll();
    }

    public Aposta preSave(Aposta aposta) {
        aposta.preco = Preco.fromQtdeDezenas(aposta.qtdeDezenas);
        aposta.dezenas = new ArrayList<Integer>();
        for (int i = 0; i < aposta.getQtdeDezenas(); i++){
            aposta.dezenas.add(0);
        }

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
