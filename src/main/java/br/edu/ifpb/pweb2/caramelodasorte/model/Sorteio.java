package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sorteios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sorteio extends BaseEntity<Long> {

    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    public BigDecimal valor;

    @ElementCollection
    public List<Integer> dezenasSorteadas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_hora", nullable = false, columnDefinition = "DATE")
    public Date dataHora;

    public String hora;

    @OneToMany(mappedBy = "sorteio")
    public List<Aposta> apostas;
}
