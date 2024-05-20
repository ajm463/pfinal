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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;

@Service
public class ServicioClases {
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
            token.usuario = usuario;
            repoToken.save(token);
        }
        return token;
    }

    public Usuario autenticacion(String tokenId) {
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
        usuario.contrasena = hashing.hash(newPassword);

        repoUsuario.save(usuario);

        return new ProfileResponse(newName, usuario.email, newTarifa);
    }

    public ProfileResponse perfilCrear(RegisterRequest register) {
        Usuario usuarioExiste = repoUsuario.findByEmail(register.email());
        if (usuarioExiste != null) {
            throw new DataIntegrityViolationException("El email ya está en uso.");
        }

        Usuario newUser = new Usuario();
        newUser.nombre = register.nombre();
        newUser.email = register.email();
        newUser.tarifa = register.tarifa();
        newUser.clasesQuedan = register.tarifa();
        newUser.clasesAsistidas = 0;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario o clase no encontrados.");
        }

        if (apuntarse && clase.plazasDisponibles > 0) {
            clase.plazasDisponibles--;
            usuario.clasesQuedan--;
            usuario.clasesAsistidas++;
        } else if (!apuntarse && clase.plazasDisponibles < clase.capacidad) {
            clase.plazasDisponibles++;
            usuario.clasesQuedan++;
            usuario.clasesAsistidas--;
        } else if (apuntarse && clase.plazasDisponibles == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay plazas disponibles.");
        } else if (!apuntarse && clase.plazasDisponibles == clase.capacidad) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no está apuntado a esta clase.");
        }

        repoUsuario.save(usuario);
        repoClase.save(clase);

        Operacion operacionNueva = new Operacion();
        operacionNueva.clase = clase;
        operacionNueva.usuario = usuario;
        operacionNueva.esInscripcion = apuntarse;
        operacionNueva.horaOperacion = LocalTime.now();
        repoOperacion.save(operacionNueva);

        return new OperacionResponse(usuario.id, operacion.clase(), operacion.apuntado());
    }
}

