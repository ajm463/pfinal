package edu.comillas.icai.gitt.pat.spring.mvc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;


@RestController
@Validated
public class ControladorRest {
    private final Map<String, ModeloContador> contadores = new HashMap<>();


    @PostMapping("/api/contadores")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ModeloContador crea(@Valid @RequestBody ModeloContador contadorNuevo, BindingResult bindingResult) {
        String nombre = contadorNuevo.nombre();
        if (contadores.containsKey(nombre)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El contador ya existe");
        }
        if (bindingResult.hasErrors()) {
            throw new ExcepcionContadorIncorrecto(bindingResult);
        }
        contadores.put(contadorNuevo.nombre(), contadorNuevo);
        return contadorNuevo;
    }

    @GetMapping("/api/contadores/{nombre}")
    public ModeloContador contador(@PathVariable String nombre) {
        ModeloContador contador = contadores.get(nombre);
        if (contador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El contador no existe");
        }
        return contador;
    }

    @PutMapping("/api/contadores/{nombre}/incremento/{incremento}")
    public ModeloContador incrementa(@PathVariable String nombre,
                                     @PathVariable Integer incremento) {
        ModeloContador contador = contadores.get(nombre);
        if (contador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El contador no existe");
        }
        ModeloContador contadorIncrementado =
                new ModeloContador(nombre, contador.valor() + incremento);
        contadores.put(nombre, contadorIncrementado);
        return contadorIncrementado;
    }

    @DeleteMapping("/api/contadores/{nombre}")
    public void elimina(@PathVariable String nombre) {
        contadores.computeIfPresent(nombre, (key, value) -> null);
    }

    @ExceptionHandler(ExcepcionContadorIncorrecto.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ModeloCampoIncorrecto> contadorIncorrecto(ExcepcionContadorIncorrecto ex) {
        return ex.getErrores().stream().map(error -> new ModeloCampoIncorrecto(
                error.getDefaultMessage(), error.getField(), error.getRejectedValue()
        )).toList();
    }


}
