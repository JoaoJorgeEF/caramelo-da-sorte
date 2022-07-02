package br.edu.ifpb.pweb2.caramelodasorte.service;

import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.repository.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SorteioService {

    @Autowired
    private SorteioRepository repo;

    public void save(Sorteio sorteio){
        sorteio.dezenasSorteadas = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++){
            sorteio.dezenasSorteadas.add(new Random().nextInt(60) + 1);
        }

        repo.save(sorteio);
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
    public void delete(Long id){
        repo.deleteById(id);
    }
}
