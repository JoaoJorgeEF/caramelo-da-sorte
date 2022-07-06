package br.edu.ifpb.pweb2.caramelodasorte.model.factory;

import br.edu.ifpb.pweb2.caramelodasorte.model.Apostador;

public class ApostadorFactory {
    public static Apostador checkAndInstantiate(Apostador apostador){
        if (apostador == null){
            return new ApostadorNull();
        }
        if (apostador.getEmail() == null){
            apostador.setEmail(new ApostadorNull().getEmail());
        }
        return apostador;
    }
}
