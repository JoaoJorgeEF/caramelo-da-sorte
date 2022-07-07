package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "apostadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apostador extends BaseEntity<Long> {

    @Column(nullable = false, length = 70)
    public String nome;

    @Column(name = "email", nullable = false)
    public String email;

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
