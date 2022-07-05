package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "apostas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Aposta extends BaseEntity<Long> {

    @ElementCollection
    public List<Integer> dezenas;

    @Column(name = "qtde_dezenas", nullable = false)
    public int qtdeDezenas;

    public Preco preco;

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Apostador apostador;

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Sorteio sorteio;

    public boolean isFavorita;
}
