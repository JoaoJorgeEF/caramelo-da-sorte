package br.edu.ifpb.pweb2.caramelodasorte.repository;

import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SorteioRepository extends JpaRepository<Sorteio, Long> {

    List<Sorteio> findByDataHoraAfter(Date date);
}
