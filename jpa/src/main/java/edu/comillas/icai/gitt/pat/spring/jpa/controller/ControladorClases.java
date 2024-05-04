package edu.comillas.icai.gitt.pat.spring.jpa.controller;


import edu.comillas.icai.gitt.pat.spring.jpa.service.ServicioClases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorClases {
    //TODO#3 DEFINIR MÉTODOS
    @Autowired
    ServicioClases servicioClases;
    /*@GetMapping("/api/alumnos")
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
    }*/

}
