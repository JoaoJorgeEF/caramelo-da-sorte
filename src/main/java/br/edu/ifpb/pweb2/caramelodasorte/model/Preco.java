package br.edu.ifpb.pweb2.caramelodasorte.model;

import java.math.BigDecimal;

public enum Preco {
    SEIS(new BigDecimal(3.00)),
    SETE(new BigDecimal(15.00)),
    OITO(new BigDecimal(90.00)),
    NOVE(new BigDecimal(300.00)),
    DEZ(new BigDecimal(1200.00));

    private final BigDecimal preco;

    Preco(BigDecimal preco){
        this.preco = preco;
    }

    public static Preco fromQtdeDezenas(int qtdeDezenas){
        switch (qtdeDezenas){
            case 6:
                return Preco.SEIS;
            case 7:
                return Preco.SETE;
            case 8:
                return Preco.OITO;
            case 9:
                return Preco.NOVE;
            case 10:
                return Preco.DEZ;
            default:
                return null;
        }
    }

    public BigDecimal getValue(){
        return this.preco;
    }
}
