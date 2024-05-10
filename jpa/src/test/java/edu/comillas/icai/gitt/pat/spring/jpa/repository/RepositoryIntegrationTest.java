package edu.comillas.icai.gitt.pat.spring.jpa.repository;

import edu.comillas.icai.gitt.pat.spring.jpa.entity.Token;
import edu.comillas.icai.gitt.pat.spring.jpa.entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class RepositoryIntegrationTest {
    @Autowired
    RepoToken repoToken;
    @Autowired RepoUsuario repoUsuario;


    private static final String NAME = "Name";
    private static final String EMAIL = "name@email.com";


    @Test
    void saveTest() {
        // Given ...
        Usuario user = new Usuario();
        user.nombre = NAME;
        user.email = EMAIL;
        user.tarifa= 10;
        user.contrasena = "aaaaaaA1";
        user.clasesAsistidas= 4;
        user.clasesQuedan= 6;


        //Guardar el usuario
        Usuario savedUser = repoUsuario.save(user);


        //Crear y guardar el token asociado
        Token token = new Token();
        token.usuario = savedUser;
        Token savedToken = repoToken.save(token);


        // When ...
        //Recupero el usuario por su email
        Usuario foundUser = repoUsuario.findByEmail(EMAIL);
        Token foundToken = repoToken.findByUsuario(savedUser);


        // Then ...
        //Verificamos que las consultas findBy estan bien : porque entonces found... no será nulo
        //Primera indicacion de que tanto save como findBy estan bien definidos
        Assertions.assertNotNull(foundUser, "El usuario debe ser encontrado.");
        Assertions.assertNotNull(foundToken, "El token debe ser encontrado.");


        //verificar que los datos coinciden para comprobar que los datos guardados son iguales que los recuperados
        //confirma que la integridad de datos se mantiene durante el guardado y la recuperacion
        Assertions.assertEquals(savedUser.nombre, foundUser.nombre, "Los nombres deben coincidir.");
        Assertions.assertEquals(savedUser.id, foundToken.usuario.id, "Los IDs de los usuarios del token deben coincidir.");
    }




    @Test void deleteCascadeTest() {
        // Given ...
        Usuario user = new Usuario();
        user.nombre = NAME;
        user.email = EMAIL;
        user.tarifa = 10;
        user.contrasena = "aaaaaaA1";
        user.clasesAsistidas=4;
        user.clasesQuedan=6;
        //Guardar el usuario


        Usuario savedUser = repoUsuario.save(user);


        //Crear y guardar el token asociado
        Token token = new Token();
        token.usuario = savedUser;
        repoToken.save(token);


        // Me aseguro de que se han guardado correctamente en la BD
        Assertions.assertEquals(1, repoUsuario.count());
        Assertions.assertEquals(1, repoToken.count());


        // When ...
        // Borrar el usuario
        repoUsuario.delete(savedUser);


        // Then ...
        // Verificar que el usuario y sus tokens asociados se hayan borrado de la BD
        Assertions.assertEquals(0, repoUsuario.count());
        Assertions.assertEquals(0, repoToken.count());
    }
}
