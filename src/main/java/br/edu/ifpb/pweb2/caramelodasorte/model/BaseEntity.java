package br.edu.ifpb.pweb2.caramelodasorte.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass

public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
