package br.edu.ifpb.pweb2.caramelodasorte.service;

import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.repository.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SorteioService {

    @Autowired
    private SorteioRepository repo;

    public void save(Sorteio sorteio){
        sorteio.dezenasSorteadas = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++){
//            sorteio.dezenasSorteadas.add(new Random().nextInt(60) + 1);
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

    public List<Sorteio> getAll(){
        return repo.findAll();
    }

    public Sorteio get(Long id){
        Optional<Sorteio> sorteio = repo.findById(id);
        if (sorteio.isPresent()){
            return sorteio.get();
        }

        return null;
    }

    public List<Sorteio> getAllWithFutureDate(Date date){
        return repo.findByDataHoraAfter(date);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
