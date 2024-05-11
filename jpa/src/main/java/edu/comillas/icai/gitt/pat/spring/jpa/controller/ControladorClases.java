package edu.comillas.icai.gitt.pat.spring.jpa.controller;


import edu.comillas.icai.gitt.pat.spring.jpa.entity.Token;
import edu.comillas.icai.gitt.pat.spring.jpa.entity.Usuario;
import edu.comillas.icai.gitt.pat.spring.jpa.model.*;
import edu.comillas.icai.gitt.pat.spring.jpa.service.ServicioClases;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ControladorClases {
    //TODO#3 DEFINIR MÉTODOS
    @Autowired
    ServicioClases servicioClases;

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody RegisterRequest register) {
        try {
            return servicioClases.perfilCrear(register); //creamos nuevo usuario
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario ya existe o datos no válidos." , e);
        }
    }

    @PostMapping("/api/users/me/session")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest credentials) { //Recibe credenciales de inicio de sesion en cuerpo solicitud
        Token token = servicioClases.login(credentials.email(), credentials.contrasena());
        if (token == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        //Si user service no autentica al usuario (devolviendo null) entonces lanzo excepcion con estado 401, unauthorized
        ResponseCookie session = ResponseCookie  //Crea nueva cookie http llamada sesion con valor id del token
                //Config de la cookie:
                .from("session", token.id)
                .httpOnly(true) //cookie NO es accesible a traves del cliente (por seguridad)
                .path("/") //cookie accesible para todos los caminos del dominio
                .sameSite("Strict") // evita que la cookie sea enviada con solicitudes de origen cruzado (proteccino a ataques CSRF)
                .build();
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, session.toString()).build();
            //devuelve Response entity con estado 200(OK), indicando que sesion se ha inciado con exito
        //Tambien anado la cookie de sesion al encabezado Set-Cookie
    }

    @DeleteMapping("/api/users/me/session")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204, peticion completada con exito pero su respuesta no tiene contenido
    //Siguiente parametro @cookievalue extrae el valor de la cookie llamada sesion de la solicitud y como sale
    // required = true,si no esta presente spring emite un erro inmediatamente
    public ResponseEntity<Void> logout(@CookieValue(value = "session", required = true) String session) {
        Usuario appUser = servicioClases.autenticacion(session);
        if (appUser == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED); //lanza la 401 si no encuentra un usuario asociado a dicha sesion (sesion invalida/caducada)
        servicioClases.logout(session);
        ResponseCookie expireSession = ResponseCookie //crea una nueva cookie llamada sesion con maxAge(0) por lo que expira de inmediato
                .from("session")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).header(HttpHeaders.SET_COOKIE, expireSession.toString()).build();
    }

    @GetMapping("/api/users/me")
    @ResponseStatus(HttpStatus.OK) //devuelve la 200 si va bien
    public ProfileResponse profile(@CookieValue(value = "session", required = true) String session) {
        Usuario appUser = servicioClases.autenticacion(session); //autetica al usuario basado en el calor de la cookie de sesion proporcionada
        if (appUser == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        //igual que antes, si no reconoce una sesion, no devuelve nada, sol un 401 de unauthorized
        return servicioClases.perfil(appUser); //me devuelve el perfil de usuario usando metodo profile en userService
    }

    @PutMapping("/api/users/me")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse update(@RequestBody ProfileRequest profile, @CookieValue(value = "session", required = true) String session) {
        //@RequestBody ProfileRequest profile --> parametro que toma los datos del perfil del usuario que se utilizan para actualizar el perfil
        Usuario appUser = servicioClases.autenticacion(session);
        if (appUser == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return servicioClases.perfilActualizar(appUser, profile); //Actualiza el perfil
    }

    @DeleteMapping("/api/users/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@CookieValue(value = "session", required = true) String session) {
        Usuario appUser = servicioClases.autenticacion(session);
        //lanzo excepcion si no encuentro el usuario o si hay otro problema;
        if (appUser == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        servicioClases.delete(appUser); //elimina el perfil del usuario autenticado
    }

    @PostMapping("/api/users/me/clase/hora")
    @ResponseStatus(HttpStatus.OK)
    public OperacionResponse apuntarse(@RequestBody OperacionRequest operacion) {
        OperacionResponse respuesta = servicioClases.apuntarse(operacion);
        if(respuesta!=null){
            return respuesta;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ha habido algún error con la solicitud");
        }

    }



}
