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
        Usuario newUser = new Usuario();
        //para acceder a datos del record hago record.variable()
        newUser.nombre = register.nombre();
        newUser.email = register.email();
        newUser.tarifa = register.tarifa();

        //CONTRASEÑA ENCRIPTADA
        newUser.contrasena = hashing.hash(register.contrasena());

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

        Boolean apuntarse = operacion.apuntado();
        Clase clase = repoClase.findByNombre(operacion.clase());
        Usuario usuario = repoUsuario.findById(operacion.usuario()).orElse(null);
        if (usuario == null || clase == null) {
            return null;
        }

        if (apuntarse && clase.plazasDisponibles>0) { //falta en este if ver que alumnos apuntados es menor que capacidad.
            clase.plazasDisponibles-=1;
            usuario.clasesQuedan -= 1;
            usuario.clasesAsistidas += 1;
        } else if(!apuntarse && clase.plazasDisponibles<clase.capacidad){
            clase.plazasDisponibles+=1; //persona se ha desapuntado
            usuario.clasesQuedan += 1;
            usuario.clasesAsistidas -= 1;
        } else if(apuntarse && clase.plazasDisponibles==0){
            return null;
        } else if(!apuntarse && clase.plazasDisponibles==clase.capacidad){
            return null;
        }

        repoUsuario.save(usuario);
        repoClase.save(clase);

        Operacion operacionNueva = new Operacion();
        operacionNueva.clase = repoOperacion.findByUsuarioId(operacion.usuario()).clase;
        operacionNueva.usuario = usuario;
        operacionNueva.horaOperacion = LocalTime.now();
        repoOperacion.save(operacionNueva);
        //TODO - Tener en cuenta la capacidad de la clase para dejar apuntarse

        return new OperacionResponse(usuario.id,operacionNueva.clase.nombre,operacion.apuntado());


    }

}
