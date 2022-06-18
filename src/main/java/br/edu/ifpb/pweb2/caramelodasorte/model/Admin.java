package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseEntity<Long> {
    @Column(nullable = false, length = 70)
    public String nome;

    @OneToOne
    @JoinColumn(name = "username")
    private Usuario user;
}
