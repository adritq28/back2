package estacion.helvetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.Estacion;
import estacion.helvetas.repository.EstacionRepository;
import estacion.helvetas.service.db.DatosEstacionServiceJpa;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/estacion")
// public class personaController {

@RestController
public class EstacionController {

    @Autowired
    private EstacionRepository estacionRepository;
    @Autowired
    private DatosEstacionServiceJpa estacionService;

    // @GetMapping
    @GetMapping("/listaEstacion")
    public List<Estacion> listarEstacion() {

        List<Estacion> a = estacionRepository.findAll();
        return estacionRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstacion(@PathVariable int id) {
        try {
            estacionService.eliminarEstacion(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la estación");
        }
    }

}
