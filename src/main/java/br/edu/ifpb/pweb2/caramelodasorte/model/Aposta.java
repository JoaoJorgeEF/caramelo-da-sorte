package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "apostas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aposta extends BaseEntity<Long> {

    @ElementCollection
    public List<Integer> dezenas;

    public Preco preco;

    @ManyToOne
    public Apostador apostador;
}
