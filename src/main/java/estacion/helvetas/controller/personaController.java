
package estacion.helvetas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.persona;
import estacion.helvetas.repository.personaRepository;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/personas")
// public class personaController {

@RestController
// @RequestMapping("/personas")
public class personaController {
    @Autowired
    private personaRepository personaRepository;

    // @GetMapping
    @GetMapping("/sol")
    public List<persona> listarPersonas() {

        List<persona> a = personaRepository.findAll();

        return personaRepository.findAll();
    }

    @GetMapping("/sol2")
    public List<persona> mostrarlistarPersonas() {
        // public String mostrarlistarPersonas() {
        List<persona> personas = personaRepository.findAll();

        // Imprimir la lista de personas
        System.out.println("Lista de personas:");
        for (persona persona : personas) {
            System.out.println(persona.toString());
        }

        // return personas[0].idPersona;
        return personas;
    }

    @GetMapping("/solucion")
    public ResponseEntity<Map<String, Object>> getAll() {
        Map<String, Object> response = new HashMap<>();
        try {

            List<persona> listpersonas = personaRepository.findAll();
            List<String> bajasList = new ArrayList<>();
            bajasList.add("Alina");
            bajasList.add("Erika");
            bajasList.add("Ramirez");
            bajasList.add("Castro");
            // bajaService.findAll().forEach(bajasList::add);
            // logger.info("Lista de Baja Inmuebles");
            // logger.info(bajasList);
            response.put("persona", bajasList);
            response.put("personas", listpersonas);
            response.put("mensaje", "Lista de bajas de inmuebles");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "No se encontraron registros");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> guardarPersona(@RequestBody persona persona) {
        try {
            persona.setIdEstacion(100000);
            persona nuevaPersona = personaRepository.save(persona);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Persona guardada con ID: " + nuevaPersona.getIdPersona());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la persona: " + e.getMessage());
        }
    }
}