package edu.comillas.icai.gitt.pat.spring.jpa.controller;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorClases {
    //TODO#3 DEFINIR MÉTODOS CONTROLLER
    /*@Autowired
    ServicioNotas servicioNotas;
    @GetMapping("/api/alumnos")
    public Iterable<Alumno> alumnos(){
        return servicioNotas.alumno();
    }

    @GetMapping("/api/alumnos/{alumno}/asignaturas")
    public List<Asignatura> asignaturasAlumno(@PathVariable Long alumno){
        return servicioNotas.asignaturaAlumno(alumno);
    }

    @GetMapping("/api/alumnos/{alumno}/asignaturas/{asignatura}/nota")
    public Integer nota(@PathVariable Long alumno, @PathVariable Long asignatura){

        return servicioNotas.nota(alumno,asignatura);
    }
    @PutMapping("/api/alumnos/{alumno}/asignaturas/{asignatura}/nota/{nuevaNota}")
    public Integer actualizaNota(@PathVariable Long alumno, @PathVariable Long asignatura, @PathVariable Integer nuevaNota){
        return servicioNotas.actualizaNota(alumno,asignatura,nuevaNota);
    }
    //verificar si está bien con los tests!! --> no se si lo he copiado igual*/
}
