package edu.comillas.icai.gitt.pat.spring.mvc;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ExcepcionContadorIncorrecto extends RuntimeException {
    private List<FieldError> errores;
    public ExcepcionContadorIncorrecto(BindingResult result) {
        this.errores = result.getFieldErrors();
    }
    public List<FieldError> getErrores() {
        return errores;
    }
}
