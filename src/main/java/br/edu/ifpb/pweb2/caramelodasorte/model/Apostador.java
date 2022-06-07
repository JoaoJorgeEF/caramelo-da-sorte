package br.edu.ifpb.pweb2.caramelodasorte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "apostadores")
public class Apostador extends BaseEntity<Long> {

    @Column(nullable = false, length = 70)
    public String nome;

    @Column(name = "data_de_nascimento", nullable = false, columnDefinition = "DATE")
    public Date dataDeNascimento;

    @Column(nullable = false, unique = true, length = 11)
    public String cpf;

    @OneToMany(mappedBy = "apostador")
    public List<Aposta> apostas;

    @OneToMany(mappedBy = "apostador")
    public List<Aposta> apostasFavoritas;
}
