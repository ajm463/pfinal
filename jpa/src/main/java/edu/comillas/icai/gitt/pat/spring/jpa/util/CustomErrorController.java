package edu.comillas.icai.gitt.pat.spring.jpa.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Puedes agregar lógica adicional aquí para manejar diferentes tipos de errores
        return "error";
    }


}

