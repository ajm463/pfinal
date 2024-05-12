package edu.comillas.icai.gitt.pat.spring.jpa.service;

import edu.comillas.icai.gitt.pat.spring.jpa.entity.Clase;
import edu.comillas.icai.gitt.pat.spring.jpa.entity.Operacion;
import edu.comillas.icai.gitt.pat.spring.jpa.entity.Token;
import edu.comillas.icai.gitt.pat.spring.jpa.entity.Usuario;
import edu.comillas.icai.gitt.pat.spring.jpa.model.*;
import edu.comillas.icai.gitt.pat.spring.jpa.repository.RepoClase;
import edu.comillas.icai.gitt.pat.spring.jpa.repository.RepoOperacion;
import edu.comillas.icai.gitt.pat.spring.jpa.repository.RepoToken;
import edu.comillas.icai.gitt.pat.spring.jpa.repository.RepoUsuario;
import edu.comillas.icai.gitt.pat.spring.jpa.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ServicioClases {
    //TODO#2 IMPLEMENTAR EL SERVICIO
    @Autowired
    RepoUsuario repoUsuario;
    @Autowired
    RepoToken repoToken;
    @Autowired
    Hashing hashing;
    @Autowired
    RepoClase repoClase;
    @Autowired
    RepoOperacion repoOperacion;

    public Token login(String email, String password) {
        Usuario usuario = repoUsuario.findByEmail(email);
        if (usuario == null || !hashing.compare(usuario.contrasena, password)) {
            return null;
        }
        Token token = repoToken.findByUsuario(usuario);
        if (token == null) {
            token = new Token();
            token.usuario = usuario; //Importante asociar el nuevo token a su usuario
            repoToken.save(token); //Guardamos el token
        }
        return token;
    }

    public Usuario autenticacion(String tokenId) {
        //como hemos definido relaciones podemos hacerlo directamente
        Token token = repoToken.findById(tokenId).orElse(null);
        if (token == null) return null;
        return token.usuario;
    }

    public ProfileResponse perfil(Usuario usuario) {
        if (usuario == null) return null;
        String email = usuario.email;
        String nombre = usuario.nombre;
        Integer tarifa = usuario.tarifa;

        return new ProfileResponse(nombre, email, tarifa);
    }

    public ProfileResponse perfilActualizar(Usuario usuario, ProfileRequest profile) {
        if (usuario == null) return null;

        String newName = profile.nombre();
        Integer newTarifa = profile.tarifa();
        String newPassword = profile.contrasena();

        usuario.nombre = newName;
        usuario.tarifa = newTarifa;
        //CONTRASEÑA ENCRIPTADA
        usuario.contrasena = hashing.hash(newPassword);

        repoUsuario.save(usuario); //me actualiza el usuario

        return new ProfileResponse(newName, usuario.email, newTarifa);
    }

    public ProfileResponse perfilCrear(RegisterRequest register) {
        System.out.println("Buscando usuario existente con email: " + register.email());
        Usuario usuarioExiste = repoUsuario.findByEmail(register.email());
        if (usuarioExiste != null) {
            System.out.println("Usuario existente encontrado: " + usuarioExiste.email);

            throw new DataIntegrityViolationException("El email ya está en uso.");


            /*return new ProfileResponse(usuarioExiste.nombre, usuarioExiste.email, usuarioExiste.tarifa);
        */}

        System.out.println("Creando nuevo usuario con email: " + register.email());
        Usuario newUser = new Usuario();
        newUser.nombre = register.nombre();
        newUser.email = register.email();
        newUser.tarifa = register.tarifa();
        newUser.clasesQuedan = register.tarifa();  // Asegúrate que esto es lo que quieres
        newUser.clasesAsistidas = 0;
        newUser.contrasena = hashing.hash(register.contrasena());

        System.out.println("Usuario creado con email: " + newUser.email + ", nombre: " + newUser.nombre);
        repoUsuario.save(newUser);

        return new ProfileResponse(newUser.nombre, newUser.email, newUser.tarifa);
    }

    public void logout(String tokenId) {
        repoToken.deleteById(tokenId);
    }

    public void delete(Usuario usuario) {
        repoUsuario.delete(usuario);
    }

    public OperacionResponse apuntarse(OperacionRequest operacion) {


        System.out.println("Iniciando proceso de apuntarse para el usuario: " + operacion.usuario());


        Boolean apuntarse = operacion.apuntado();
        Clase clase = repoClase.findByNombre(operacion.clase());
        System.out.println("Clase" + clase);
        Usuario usuario = repoUsuario.findById(operacion.usuario()).orElse(null);
        System.out.println("Usuario" + usuario);
        if (usuario == null || clase == null) {
            System.out.println("Usuario o clase no encontrados.");
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o clase no encontrados.");
        }


        System.out.println("Usuario y clase encontrados, procediendo...");


        if (apuntarse && clase.plazasDisponibles>0) { //falta en este if ver que alumnos apuntados es menor que capacidad.
            System.out.println("Apuntando al usuario...");


            clase.plazasDisponibles-=1;
            usuario.clasesQuedan -= 1;
            usuario.clasesAsistidas += 1;
        } else if(!apuntarse && clase.plazasDisponibles<clase.capacidad){
            System.out.println("Desapuntando al usuario...");


            clase.plazasDisponibles+=1; //persona se ha desapuntado
            usuario.clasesQuedan += 1;
            usuario.clasesAsistidas -= 1;
        } else if(apuntarse && clase.plazasDisponibles==0){
            System.out.println("No se puede realizar la operación solicitada.");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay plazas disponibles.");
        } else if(!apuntarse && clase.plazasDisponibles==clase.capacidad){
            return null;
        }


        repoUsuario.save(usuario);
        repoClase.save(clase);


        Operacion operacionNueva = new Operacion();
        operacionNueva.clase = clase;
        operacionNueva.usuario = usuario;
        operacionNueva.esInscripcion = apuntarse;
        operacionNueva.horaOperacion = LocalTime.now();
        repoOperacion.save(operacionNueva);
        //TODO - Tener en cuenta la capacidad de la clase para dejar apuntarse


        System.out.println("Operación realizada correctamente.");


        return new OperacionResponse(usuario.id,operacionNueva.clase.nombre,operacion.apuntado());




    }


}
