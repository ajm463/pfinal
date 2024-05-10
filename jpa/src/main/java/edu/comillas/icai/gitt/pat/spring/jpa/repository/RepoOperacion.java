package edu.comillas.icai.gitt.pat.spring.jpa.repository;

import edu.comillas.icai.gitt.pat.spring.jpa.entity.Operacion;
import edu.comillas.icai.gitt.pat.spring.jpa.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RepoOperacion extends CrudRepository<Operacion, Long> {
    Operacion findByUsuario(Usuario usuario);

    Operacion findByUsuarioId(Long usuario);
}
