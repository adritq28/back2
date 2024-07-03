package estacion.helvetas.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/comparacion/{idFenologia}/{idZona}")
    public Map<String, Object> comparacionDatos(@PathVariable int idFenologia, @PathVariable int idZona) {
        List<Object[]> listaPronostico = datosPronosticoRepository.comparacionDatosPronostico(idFenologia, idZona);

        Map<String, Object> closestDateData = null;
        long minDifference = Long.MAX_VALUE;
        LocalDate currentDate = LocalDate.now();

        for (Object[] usuarioConFenologia : listaPronostico) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idPronostico", usuarioConFenologia[0]);
            usuario.put("idFenologia", usuarioConFenologia[1]);
            usuario.put("idZona", usuarioConFenologia[2]);
            usuario.put("idCultivo", usuarioConFenologia[3]);
            usuario.put("idUmbrales", usuarioConFenologia[4]);
            usuario.put("fase", usuarioConFenologia[5]);
            usuario.put("tempMax", usuarioConFenologia[6]);
            usuario.put("tempMin", usuarioConFenologia[7]);
            usuario.put("pcpn", usuarioConFenologia[8]);
            usuario.put("fecha", usuarioConFenologia[9]);
            usuario.put("descripcion", usuarioConFenologia[10]);
            usuario.put("nroDias", usuarioConFenologia[11]);
            usuario.put("tempMaxPronostico", usuarioConFenologia[12]);
            usuario.put("tempMinPronostico", usuarioConFenologia[13]);
            usuario.put("pcpnPronostico", usuarioConFenologia[14]);

            // Convertir la fecha a LocalDate
            LocalDate fecha = ((Date) usuarioConFenologia[9]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long difference = Math.abs(ChronoUnit.DAYS.between(currentDate, fecha));

            // Comparar los valores y determinar la alerta
            Float tempMax = (Float) usuarioConFenologia[6];
            Float tempMin = (Float) usuarioConFenologia[7];
            Float pcpn = (Float) usuarioConFenologia[8];

            Float tempMaxPronostico = (Float) usuarioConFenologia[12];
            Float tempMinPronostico = (Float) usuarioConFenologia[13];
            Float pcpnPronostico = (Float) usuarioConFenologia[14];

            String alerta = "";

            if (Math.abs(tempMax - tempMaxPronostico) > 5 ||
                    Math.abs(tempMin - tempMinPronostico) > 5 ||
                    Math.abs(pcpn - pcpnPronostico) > 5) {
                alerta = "ALERTA ROJA";
            } else if (Math.abs(tempMax - tempMaxPronostico) > 3 ||
                    Math.abs(tempMin - tempMinPronostico) > 3 ||
                    Math.abs(pcpn - pcpnPronostico) > 3) {
                alerta = "ALERTA AMARILLA";
            } else {
                alerta = "SIN ALERTA";
            }

            // if (diferenciaTempMax > 5 || diferenciaTempMin > 5 || diferenciaPcpn > 5) {
            // alerta = "ALERTA ROJA";
            // } else if (diferenciaTempMax > 3 || diferenciaTempMin > 3 || diferenciaPcpn >
            // 3) {
            // alerta = "ALERTA AMARILLA";
            // } else {
            // alerta = "SIN ALERTA";
            // }

            usuario.put("alerta", alerta);

            // Comprobar si esta diferencia de fecha es la más pequeña que hemos visto
            if (difference < minDifference) {
                minDifference = difference;
                closestDateData = usuario;
            }
        }

        return closestDateData;
    }

}
