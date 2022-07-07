package br.edu.ifpb.pweb2.caramelodasorte.service.chainOfResponsability;

import br.edu.ifpb.pweb2.caramelodasorte.model.Aposta;
import br.edu.ifpb.pweb2.caramelodasorte.model.Apostador;
import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.model.factory.ApostadorFactory;
import br.edu.ifpb.pweb2.caramelodasorte.repository.ApostaRepository;
import br.edu.ifpb.pweb2.caramelodasorte.service.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SorteioMiddleware extends Middleware{

    @Autowired
    private ApostaRepository apostaRepo;

    @Override
    public Boolean check(Sorteio sorteio, Apostador apostador) {
        List<Aposta> apostas = apostaRepo.findBySorteioIdOrderByDataDeRegistro(sorteio.getId());

        for (Aposta aposta : apostas) {
            List<Integer> acertos = aposta.getDezenas().stream().filter(d -> sorteio.getDezenasSorteadas().contains(d)).collect(Collectors.toList());
            if (acertos.size() >= 6){
                aposta.setVencedora(true);
                apostador = ApostadorFactory.checkAndInstantiate(aposta.getApostador());
                break;
            } else{
                apostador = ApostadorFactory.checkAndInstantiate(new Apostador());
            }
        }
        apostaRepo.saveAll(apostas);

        return checkNext(sorteio, apostador);
    }
}
