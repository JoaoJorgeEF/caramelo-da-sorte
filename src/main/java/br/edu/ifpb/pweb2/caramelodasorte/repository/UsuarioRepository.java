package br.edu.ifpb.pweb2.caramelodasorte.repository;

import br.edu.ifpb.pweb2.caramelodasorte.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
