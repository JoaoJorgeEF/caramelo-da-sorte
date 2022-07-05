package br.edu.ifpb.pweb2.caramelodasorte.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends BaseEntity<Long> {

    private String username;
    private String password;
    private Boolean enabled;

    @OneToMany(mappedBy = "username")
    @ToString.Exclude
    List<Authority> authorities;
}
