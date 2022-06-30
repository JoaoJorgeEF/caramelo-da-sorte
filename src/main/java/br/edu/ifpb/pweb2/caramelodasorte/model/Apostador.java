package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "data_de_nascimento", nullable = false, columnDefinition = "DATE")
    public Date dataDeNascimento;

    @Column(nullable = false, unique = true, length = 11)
    public String cpf;

    @OneToOne
    @JoinColumn(name = "username")
    //@Column(name = "usuario", nullable = false, length = 40)
    private Usuario user;
    //private String usuario;

    @OneToMany(mappedBy = "apostador")
    public List<Aposta> apostas;

    @OneToMany(mappedBy = "apostador")
    public List<Aposta> apostasFavoritas;
}
