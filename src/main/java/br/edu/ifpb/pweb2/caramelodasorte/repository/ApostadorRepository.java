package br.edu.ifpb.pweb2.caramelodasorte.repository;

import br.edu.ifpb.pweb2.caramelodasorte.model.Apostador;
import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ApostadorRepository extends JpaRepository<Apostador, Long> {

    Apostador findByUserUsername(String username);
}
