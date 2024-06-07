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

import estacion.helvetas.model.DatosEstacionDTO;
import estacion.helvetas.model.DatosEstacionHidrologica;
import estacion.helvetas.repository.DatosEstacionHidrologicaRepository;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/datosHidrologica")
// public class personaController {

@RestController
public class DatosEstacionHidrologicaController {

    @Autowired
    private DatosEstacionHidrologicaRepository datosEstacionRepository;

    @PostMapping("/addDatosHidrologica")
    public ResponseEntity<String> guardarDatosHidrologica(@RequestBody DatosEstacionHidrologica datosEstacion) {
        System.out.println("--------" + datosEstacion.toString());
        try {
            // persona.setIdEstacion(100000);
            System.out.println("--+++++--" + datosEstacion.toString());
            DatosEstacionHidrologica nuevodatosEstacion = datosEstacionRepository.save(datosEstacion);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("datos estacion guardada con ID: " + nuevodatosEstacion.getIdHidrologica());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la datos estacion: " + e.getMessage());
        }
    }

    @GetMapping("/listaHidrologica/{id}")
    public ResponseEntity<List<DatosEstacionDTO>> obtenerDatosObservador(@PathVariable int id) {
        List<Object[]> resultados = datosEstacionRepository.obtenerDatosHidrologica(id);
        List<DatosEstacionDTO> observadores = new ArrayList<>();

        for (Object[] resultado : resultados) {
            int idUsuario = (int) resultado[0];
            if (idUsuario == id) {
                DatosEstacionDTO observador = new DatosEstacionDTO();
                observador.setIdUsuario(idUsuario);
                observador.setMunicipio((String) resultado[1]);
                observador.setEstacion((String) resultado[2]);
                observador.setTipoEstacion((String) resultado[3]);
                observador.setNombreCompleto((String) resultado[4]);
                observador.setFechaReg((Timestamp) resultado[5]);
                observador.setLimnimetro((Float) resultado[6]);
                observador.setIdEstacion((int) resultado[7]);
                observador.setDelete((boolean) resultado[8]);
                observadores.add(observador);
            }
        }

        if (observadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(observadores);
    }

    @GetMapping("/hidrologica/{id}")
    public ResponseEntity<List<DatosEstacionDTO>> obtenerLista(@PathVariable int id) {
        List<Object[]> resultados = datosEstacionRepository.obtenerListaHidrologica(id);
        List<DatosEstacionDTO> observadores = new ArrayList<>();
        System.out.println("sssssssssss");
        for (Object[] resultado : resultados) {
            int idEstacion = (int) resultado[0];
            if (idEstacion == id) {
                DatosEstacionDTO observador = new DatosEstacionDTO();
                observador.setIdEstacion(idEstacion);
                observador.setMunicipio((String) resultado[1]);
                observador.setEstacion((String) resultado[2]);
                observador.setTipoEstacion((String) resultado[3]);
                observador.setNombreCompleto((String) resultado[4]);
                observador.setFechaReg((Timestamp) resultado[5]);
                observador.setLimnimetro((Float) resultado[6]);
                observador.setDelete((boolean) resultado[7]);
                observadores.add(observador);
            }
        }

        if (observadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(observadores);
    }

}
