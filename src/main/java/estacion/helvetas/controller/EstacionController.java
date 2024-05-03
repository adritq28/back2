package estacion.helvetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.Estacion;
import estacion.helvetas.repository.EstacionRepository;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/estacion")
// public class personaController {

@RestController
public class EstacionController {

    @Autowired
    private EstacionRepository estacionRepository;

    // @GetMapping
    @GetMapping("/listaEstacion")
    public List<Estacion> listarEstacion() {

        List<Estacion> a = estacionRepository.findAll();
        return estacionRepository.findAll();
    }

}
