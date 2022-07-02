package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "apostas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aposta extends BaseEntity<Long> {

    @ElementCollection
    public List<Integer> dezenas;

    @Column(name = "qtde_dezenas", nullable = false)
    public int qtdeDezenas;

    public Preco preco;

    @ManyToOne
    public Apostador apostador;

    @ManyToOne
    public Sorteio sorteio;
}
