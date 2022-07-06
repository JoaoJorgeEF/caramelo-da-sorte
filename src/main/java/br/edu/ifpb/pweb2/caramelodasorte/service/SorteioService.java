package br.edu.ifpb.pweb2.caramelodasorte.service;

import br.edu.ifpb.pweb2.caramelodasorte.model.Aposta;
import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.repository.ApostaRepository;
import br.edu.ifpb.pweb2.caramelodasorte.repository.SorteioRepository;
import br.edu.ifpb.pweb2.caramelodasorte.service.proxy.SorteioProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SorteioService implements SorteioProxy {

    @Autowired
    private SorteioRepository repo;

    @Autowired
    private ApostaRepository apostaRepo;

    public void save(Sorteio sorteio){
        sorteio.dezenasSorteadas = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++){
            sorteio.dezenasSorteadas.add(0);
        }

        repo.save(sorteio);
    }

    public Sorteio saveDezenas(Sorteio sorteio){
        Optional<Sorteio> foundOSorteio = repo.findById(sorteio.getId());
        Sorteio foundSorteio = null;
        if (foundOSorteio.isPresent()){
            foundSorteio = foundOSorteio.get();
            foundSorteio.setDezenasSorteadas(sorteio.getDezenasSorteadas());
            repo.save(foundSorteio);
        }

        return foundSorteio;
    }

    public List<Sorteio> getAllWithFutureDate(){
        return repo.findAll();
    }

    public Sorteio get(Long id){
        Optional<Sorteio> sorteio = repo.findById(id);
        if (sorteio.isPresent()){
            return sorteio.get();
        }

        return null;
    }

    public void checkWinner(Long id){
        List<Aposta> apostas = apostaRepo.findBySorteioIdOrderByDataDeRegistro(id);
        Sorteio sorteio = get(id);

        for (Aposta aposta : apostas) {
            List<Integer> acertos = aposta.getDezenas().stream().filter(d -> sorteio.getDezenasSorteadas().contains(d)).collect(Collectors.toList());
            if (acertos.size() >= 6) aposta.setVencedora(true);
            break;
        }
        apostaRepo.saveAll(apostas);
    }

    public List<Sorteio> getAllWithFutureDate(Date date){
        return repo.findByDataHoraAfter(date);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
