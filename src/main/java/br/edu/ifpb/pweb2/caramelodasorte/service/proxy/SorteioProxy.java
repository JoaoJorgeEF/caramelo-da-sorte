package br.edu.ifpb.pweb2.caramelodasorte.service.proxy;

import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;

import java.util.HashMap;
import java.util.List;

public interface SorteioProxy {

    List<Sorteio> getAllWithFutureDate();

}
