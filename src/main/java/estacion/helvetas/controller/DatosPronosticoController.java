package estacion.helvetas.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.DatosPronostico;
import estacion.helvetas.model.DatosPronosticoDTO;
import estacion.helvetas.repository.DatosPronosticoRepository;
import estacion.helvetas.service.db.DatosPronosticoServiceJpa;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/datos_pronostico")

@RestController
public class DatosPronosticoController {

    @Autowired
    private DatosPronosticoRepository datosPronosticoRepository;

    @Autowired
    private DatosPronosticoServiceJpa pronosticoService;

    @PostMapping("/addDatosPronostico")
    public ResponseEntity<String> guanrdarDatosPronostico(@RequestBody DatosPronostico datosPronostico) {
        System.out.println("--------" + datosPronostico.toString());
        try {
            System.out.println("--+++++--" + datosPronostico.toString());
            DatosPronostico nuevodatosPronostico = datosPronosticoRepository.save(datosPronostico);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("datos estacion guardada con ID: " + nuevodatosPronostico.getIdPronostico());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la datos estacion: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/{idZona}")
    public ResponseEntity<List<DatosPronosticoDTO>> obtenerDatosPronostico(@PathVariable int id,
            @PathVariable int idZona) {
        List<Object[]> resultados = datosPronosticoRepository.obtenerDatosPronostico(id, idZona);
        List<DatosPronosticoDTO> Pronosticoes = new ArrayList<>();

        for (Object[] resultado : resultados) {
            int idUsuario = (int) resultado[0];
            if (idUsuario == id) {
                DatosPronosticoDTO Pronostico = new DatosPronosticoDTO();
                Pronostico.setIdUsuario(idUsuario);
                Pronostico.setMunicipio((String) resultado[1]);
                Pronostico.setZona((String) resultado[2]);
                Pronostico.setNombreCompleto((String) resultado[3]);
                Pronostico.setFecha((Timestamp) resultado[4]);
                Pronostico.setTempMin((Float) resultado[5]);
                Pronostico.setTempMax((Float) resultado[6]);
                Pronostico.setPcpn((Float) resultado[7]);
                Pronostico.setIdFenologia((int) resultado[8]);
                Pronosticoes.add(Pronostico);
            }
        }

        if (Pronosticoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Pronosticoes);
    }

}
