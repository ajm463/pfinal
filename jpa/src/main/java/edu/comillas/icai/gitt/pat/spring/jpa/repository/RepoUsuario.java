package edu.comillas.icai.gitt.pat.spring.jpa.repository;

import edu.comillas.icai.gitt.pat.spring.jpa.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RepoUsuario extends CrudRepository<Usuario, Long> {

    //TODO#1 Definir REPOS
    Usuario findByEmail(String email);

}
