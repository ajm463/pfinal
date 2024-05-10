package edu.comillas.icai.gitt.pat.spring.jpa.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;


import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;


class RegisterRequestUnitTest {


    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        // Given ...
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                10, "aaaaaaA1");
        // When ...
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registro);
        // Then ...
        assertTrue(violations.isEmpty()); //confirma que no ha habido ninguna violacion
    }


    @Test
    public void testInvalidPassword_NoUppercase() {
        // Given
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                10, "aaaaaaa1"); // Contraseña sin mayúsculas


        // When
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registro);


        // Then
        assertEquals(1, violations.size());
        // Debería haber una violación de la restricción (solo 1 porque el resto de datos son correcto)
    }


    @Test
    public void testInvalidEmail() {
        // Given ...
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombreemail", //Email sin @
                10, "aaaaaaA1");
        // When ...
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        // Then ...
        assertFalse(violations.isEmpty());
        //confirmo que efectivamente violations no esta empty, porque el email no tiene formato correcto
        //puedo hacer esto o hacer lo de antes, que implica que hay una violacion


    }


    @Test
    public void testNameIsNull() {
        //Given
        RegisterRequest registro = new RegisterRequest(
                null, "nombre@email",
                10, "aaaa1aaA");
        //When
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        //then
        assertFalse(violations.isEmpty(), "Debería haber errores de validación ya que el nombre es nulo");


        // Opcional: Comprobar que el mensaje de violación específico para el campo 'name' es correcto
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("nombre")),
                "Debería haber un error de validación específico para el campo 'nombre'");


    }

}

