package edu.comillas.icai.gitt.pat.spring.jpa.repository;

import edu.comillas.icai.gitt.pat.spring.jpa.entity.Token;
import edu.comillas.icai.gitt.pat.spring.jpa.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RepoToken extends CrudRepository<Token,String> {
    Token findByUsuario(Usuario usuario);
}
