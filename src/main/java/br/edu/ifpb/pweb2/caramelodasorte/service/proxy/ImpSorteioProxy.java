package br.edu.ifpb.pweb2.caramelodasorte.service.proxy;

import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.service.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class ImpSorteioProxy implements SorteioProxy {

    private List<Sorteio> cacheSorteios = new ArrayList<>();

    @Autowired
    private SorteioService sorteioService;

    @Override
    public List<Sorteio> getAllWithFutureDate() {
        if(cacheSorteios.isEmpty()){
            cacheSorteios = sorteioService.getAllWithFutureDate();
        }
        return cacheSorteios;
    }

    public void refreshCache() {
        cacheSorteios = sorteioService.getAllWithFutureDate();
    }
}
