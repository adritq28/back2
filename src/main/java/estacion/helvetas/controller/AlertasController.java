package estacion.helvetas.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.DatosPronostico;
import estacion.helvetas.service.db.DatosPronosticoServiceJpa;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/alertas")
public class AlertasController {

    @Autowired
    private DatosPronosticoServiceJpa datosPronosticoService;

    @GetMapping("/{cultivoId}")
    public ResponseEntity<List<String>> generarAlertas(@PathVariable int cultivoId) {
        List<String> alertas = datosPronosticoService.generarAlertas(cultivoId);
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/ultima/{cultivoId}")
    public ResponseEntity<Map<String, String>> obtenerUltimaAlerta(@PathVariable int cultivoId) {
        Map<String, String> alertas = datosPronosticoService.generarUltimaAlerta(cultivoId);
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/pcpnFase/{cultivoId}")
    public ResponseEntity<List<Map<String, Object>>> obtenerPcpnFase(@PathVariable int cultivoId) {
        List<Map<String, Object>> pcpnFaseList = datosPronosticoService.generarPcpnFase(cultivoId);
        return ResponseEntity.ok(pcpnFaseList);
    }

    @GetMapping("/pronostico_fase/{cultivoId}")
    public List<DatosPronostico> obtenerPronosticosFase(@PathVariable int cultivoId) {
        return datosPronosticoService.pronosticosFase(cultivoId);
    }

    @GetMapping("/fase/{cultivoId}")
    public int obtenerFaseActual(@PathVariable int cultivoId) {
        return datosPronosticoService.obtenerFaseActual(cultivoId);
    }

    private String determinarNivelAlerta(String alerta) {
        if (alerta.contains("ROJA")) {
            return "rojo";
        } else if (alerta.contains("AMARILLA")) {
            return "amarillo";
        } else if (alerta.contains("VERDE")) {
            return "verde";
        } else {
            return "no definido";
        }
    }
}
