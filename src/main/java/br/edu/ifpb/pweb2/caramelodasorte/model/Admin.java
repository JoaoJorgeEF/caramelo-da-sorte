package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseEntity<Long> {
    @Column(nullable = false, length = 70)
    public String nome;

    @OneToOne
    @JoinColumn(name = "username")
    private Usuario user;
}
