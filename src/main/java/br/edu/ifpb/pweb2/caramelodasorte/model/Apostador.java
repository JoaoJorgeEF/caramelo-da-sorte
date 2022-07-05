package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "apostadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apostador extends BaseEntity<Long> {

    @Column(nullable = false, length = 70)
    public String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_de_nascimento", nullable = false, columnDefinition = "DATE")
    public Date dataDeNascimento;

    @Column(nullable = false, unique = true, length = 11)
    public String cpf;

    @OneToOne
    @JoinColumn(name = "username")
    private Usuario user;

    @OneToMany(mappedBy = "apostador")
    public List<Aposta> apostas;
}
