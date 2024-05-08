package edu.comillas.icai.gitt.pat.spring.jpa.repository;

import edu.comillas.icai.gitt.pat.spring.jpa.entity.Operacion;
import org.springframework.data.repository.CrudRepository;

public interface RepoOperacion extends CrudRepository<Operacion, Long> {
}
