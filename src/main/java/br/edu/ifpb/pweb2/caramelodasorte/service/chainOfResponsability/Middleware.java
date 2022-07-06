package br.edu.ifpb.pweb2.caramelodasorte.service.chainOfResponsability;

import br.edu.ifpb.pweb2.caramelodasorte.model.Apostador;
import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;

public abstract class Middleware {

    private Middleware next;

    public Middleware linkWith(Middleware next){
        this.next = next;
        return this.next;
    }

    public abstract Boolean check(Sorteio sorteio, Apostador apostador);

    public boolean checkNext(Sorteio sorteio, Apostador apostador ) {
        if (next == null) {
            return true;
        }
        return next.check(sorteio, apostador);
    }
}
