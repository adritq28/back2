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

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/municipio")

@RestController
public class MunicipioController {

    @Autowired
    private MunicipioRepository municipioRepository;

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
            municipio.put("nombreCultivo", municipioConZona[4]);
            municipio.put("idCultivo", municipioConZona[5]);
            municipio.put("latitud", municipioConZona[6]);
            municipio.put("longitud", municipioConZona[7]);
            municipioZona.add(municipio);
        }
        return municipioZona;
    }

    @GetMapping("/zonaaa/{idMunicipio}")
    public List<Map<String, Object>> buscaZonasPorCultivo(@PathVariable int idMunicipio) {
        List<Map<String, Object>> municipioZona = new ArrayList<>();
        List<Object[]> listamunicipioZona = municipioRepository.obtenerZonaPorCultivo(idMunicipio);
        for (Object[] municipioConZona : listamunicipioZona) {
            Map<String, Object> municipio = new HashMap<>();
            municipio.put("nombreCultivo", municipioConZona[0]);
            municipio.put("nombreMunicipio", municipioConZona[1]);
            municipioZona.add(municipio);
        }
        return municipioZona;
    }

    @GetMapping("/cultivo/{idMunicipio}")
    public List<Map<String, Object>> buscaCultivoPorMunicipio(@PathVariable int idMunicipio) {
        List<Map<String, Object>> municipioZona = new ArrayList<>();
        List<Object[]> listamunicipioZona = municipioRepository.obtenerCultivoPorMunicipio(idMunicipio);
        for (Object[] municipioConZona : listamunicipioZona) {
            Map<String, Object> municipio = new HashMap<>();
            municipio.put("nombreMunicipio", municipioConZona[0]);
            municipio.put("nombreCultivo", municipioConZona[1]);
            municipioZona.add(municipio);
        }
        return municipioZona;
    }

}
