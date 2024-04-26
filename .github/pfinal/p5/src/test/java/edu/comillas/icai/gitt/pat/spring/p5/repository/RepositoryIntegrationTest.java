package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */

    @Test void saveTest() {
        // Given
        AppUser user = new AppUser();
        user.name = "Aurora";
        user.email = "aurora@email.com";
        user.password = "JuncoMi1";
        user.role= Role.USER;

        Token token = new Token();
        token.appUser = user;

        // When
        appUserRepository.save(user);
        tokenRepository.save(token);

        AppUser savedUser = appUserRepository.findByEmail(user.email);
        Token savedToken = tokenRepository.findByAppUserId(user.id);

        // Then
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(user.name, savedUser.name);
        Assertions.assertEquals(user.email, savedUser.email);
        Assertions.assertEquals(user.password, savedUser.password);
        Assertions.assertEquals(user.role, savedUser.role);

        Assertions.assertNotNull(savedToken);
        Assertions.assertEquals(token.id, savedToken.id);
        Assertions.assertEquals(token.appUser, savedToken.appUser);
    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */

    @Test void deleteCascadeTest() {
        // Given
        AppUser user = new AppUser();
        Token token = new Token();
        user.name ="Aurora";
        user.email="aurora@email.com";
        user.password="JuncoMi1";
        user.role=Role.USER;
        token.appUser=user;

        appUserRepository.save(user);
        tokenRepository.save(token);

        // When
        appUserRepository.delete(user);

        // Then
        Assertions.assertEquals(0, appUserRepository.count());
        Assertions.assertEquals(0, tokenRepository.count());
    }
}



