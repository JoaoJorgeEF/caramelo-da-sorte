package br.edu.ifpb.pweb2.caramelodasorte.repository;

import br.edu.ifpb.pweb2.caramelodasorte.model.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

    List<Aposta> findBySorteioIdOrderByDataDeRegistro(Long id);
}
