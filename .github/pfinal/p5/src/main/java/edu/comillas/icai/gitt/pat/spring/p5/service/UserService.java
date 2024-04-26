package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.*;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {
    @Autowired AppUserRepository appUserRepository;
    //solo va a funcionar porque ponemos @service --> si no no va a ser enchufable
    @Autowired TokenRepository tokenRepository;
    //estas clases autowired van a orquestar las operaciones sobre las tablas
    @Autowired Hashing hashing;
    public Token login(String email, String password) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null || !hashing.compare(appUser.password, password)) {
            return null;
        }
        Token token = tokenRepository.findByAppUserId(appUser.id);
        if (token == null) {
            token = new Token();
            token.appUser = appUser;
            tokenRepository.save(token);
        }
        return token;
        /*if (appUser != null) {
            if(appUser.password == password) {
                Token token = tokenRepository.findByAppUserId(appUser.id);

                if (token == null) {
                    token = new Token();
                    token.appUser = appUser;
                    tokenRepository.save(token);
                }
                return token;
            }
        }

        return null;*/ //hecho sin hashing
    }

    public AppUser authentication(String tokenId) {
        Token token = tokenRepository.findById(tokenId).orElse(null);
        AppUser usuario = token.appUser;
        //si pongo optional token.isEmpty() porque si no te puede devolver algo nulo si no lo encuentra
        if(usuario!=null){
            return usuario;
        }
        //throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return null;
    }

    public ProfileResponse profile(AppUser appUser) {
        if (appUser == null) return null;
        return new ProfileResponse(appUser.name, appUser.email, appUser.role);
    }
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        if (appUser == null) return null;
        String nombre = profile.name();
        Role rol = profile.role();
        String contraseña = profile.password();
        appUser.name = nombre;
        appUser.role = rol;

        //appUser.password = contraseña;

        //con hashing
        appUser.password = hashing.hash(contraseña);

        appUserRepository.save(appUser);
        String email = appUser.email;
        return new ProfileResponse(nombre, email, rol);
    }

    public ProfileResponse profile(RegisterRequest register) {
        AppUser nuevo = new AppUser();

        nuevo.name = register.name();
        nuevo.email = register.email();
        nuevo.role = register.role();
        //nuevo.password = register.password();
        nuevo.password = hashing.hash(register.password());
        appUserRepository.save(nuevo);
        return new ProfileResponse(nuevo.name, nuevo.email, nuevo.role);
    }


    public void logout(String tokenId) {
        Token token = tokenRepository.findById(tokenId).orElse(null);
        if(token!=null){
            tokenRepository.delete(token);
        }
    }

    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }



}

