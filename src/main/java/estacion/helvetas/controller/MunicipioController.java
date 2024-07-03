package estacion.helvetas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.repository.MunicipioRepository;
import estacion.helvetas.service.db.DatosPronosticoServiceJpa;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/municipio")

@RestController
public class MunicipioController {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private DatosPronosticoServiceJpa pronosticoService;

    @GetMapping("/zona/{id}")
    public List<Map<String, Object>> buscaZonas(@PathVariable int id) {
        List<Map<String, Object>> municipioZona = new ArrayList<>();
        List<Object[]> listamunicipioZona = municipioRepository.obtenerZonasMunicipio(id);
        for (Object[] municipioConZona : listamunicipioZona) {
            Map<String, Object> municipio = new HashMap<>();
            municipio.put("idMunicipio", municipioConZona[0]);
            municipio.put("nombreMunicipio", municipioConZona[1]);
            municipio.put("idZona", municipioConZona[2]);
            municipio.put("nombreZona", municipioConZona[3]);
            municipioZona.add(municipio);
        }
        return municipioZona;
    }

}
