package br.edu.ifpb.pweb2.caramelodasorte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity<Long> {
    @Column(nullable = false, length = 70)
    public String nome;
}
