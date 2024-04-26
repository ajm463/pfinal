package edu.comillas.icai.gitt.pat.spring.mvc;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class ControladorSSR {
    @GetMapping("/saludo")
    public String saludo(
            @RequestParam(name="nombre", required=false, defaultValue="Mundo") String nombre,
            Model model
    ) {
        model.addAttribute("nombre", nombre);
        return "saludo";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("contacto", new ModeloFormularioContacto("",""));
        return "contacto";
    }

    @PostMapping("/contacto")
    public String contacto(
            @Valid @ModelAttribute("contacto")
            ModeloFormularioContacto contacto,
            BindingResult result,
            Model model
    ) {
        if (!result.hasErrors()) {
            model.addAttribute("exito",
                    "Gracias " + contacto.email() + ", tu mensaje ha sido recibido.");
        }
        return "contacto";
    }

}

