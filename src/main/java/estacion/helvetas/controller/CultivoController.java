package estacion.helvetas.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.Cultivo;
import estacion.helvetas.repository.CultivoRepository;
import estacion.helvetas.service.db.CultivoServiceJpa;

@CrossOrigin(origins = "*")
// @RestController
@RestController
@RequestMapping("/cultivos")
public class CultivoController {

    @Autowired
    private CultivoServiceJpa cultivoService;

    @Autowired
    private CultivoRepository cultivoRepo;

    // @PutMapping("/{idCultivo}/fecha_siembra")
    // public ResponseEntity<Cultivo> actualizarFechaSiembra(
    // @PathVariable int idCultivo,
    // @RequestBody Timestamp nuevaFechaSiembra) {
    // Cultivo cultivoActualizado = cultivoService.actualizarFechaSiembra(idCultivo,
    // nuevaFechaSiembra);
    // return ResponseEntity.ok(cultivoActualizado);
    // }

    // @PutMapping("/{id}/fecha-siembra")
    // public ResponseEntity<Cultivo> actualizarFechaSiembra(@PathVariable int id,
    // @RequestParam("fechaSiembra") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    // Date nuevaFechaSiembra) {

    // Optional<Cultivo> optionalCultivo = cultivoRepo.findById(id);
    // if (optionalCultivo.isPresent()) {
    // Cultivo cultivo = optionalCultivo.get();
    // cultivo.setFechaSiembra(nuevaFechaSiembra);
    // cultivoRepo.save(cultivo);
    // return ResponseEntity.ok(cultivo);
    // } else {
    // return ResponseEntity.notFound().build();
    // }
    // }

    @PostMapping("/addCultivo")
    public ResponseEntity<String> guanrdarCultivo(@RequestBody Cultivo Cultivo) {
        System.out.println("--------" + Cultivo.toString());
        try {
            System.out.println("--+++++--" + Cultivo.toString());
            Cultivo nuevoCultivo = cultivoRepo.save(Cultivo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("datos estacion guardada con ID: " + nuevoCultivo.getIdCultivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la datos estacion: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/fecha-siembra")
    public ResponseEntity<Cultivo> actualizarFechaSiembra(@PathVariable int id,
            @RequestParam("fechaSiembra") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date nuevaFechaSiembra) {

        // Ajusta la zona horaria si es necesario, por ejemplo, a UTC
        Instant instant = nuevaFechaSiembra.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        Date fechaAjustada = Date.from(zonedDateTime.toInstant());

        Optional<Cultivo> optionalCultivo = cultivoRepo.findById(id);
        if (optionalCultivo.isPresent()) {
            Cultivo cultivo = optionalCultivo.get();
            cultivo.setFechaSiembra(fechaAjustada);
            cultivoRepo.save(cultivo);
            return ResponseEntity.ok(cultivo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lista_datos_cultivo/{idZona}")
    public List<Map<String, Object>> listaDatosFechaSiembra(@PathVariable int idZona) {
        List<Map<String, Object>> cultivo = new ArrayList<>();
        List<Object[]> listaZona = cultivoRepo.listaDatosFechaSiembra(idZona);

        for (Object[] datos : listaZona) {
            // Obtener el valor de 'delete'
            Boolean delete = (Boolean) datos[3];

            // Si 'delete' es true, no agregar el registro a la lista
            if (delete == null || !delete) {
                Map<String, Object> registro = new HashMap<>();
                registro.put("fechaSiembra", datos[0]);
                registro.put("nombre", datos[1]);
                registro.put("idCultivo", datos[2]);
                registro.put("delete", delete);
                registro.put("fechaReg", datos[4]);
                registro.put("tipo", datos[5]);
                cultivo.add(registro);
            }
        }

        return cultivo;
    }

    @PostMapping("/editar")
    public ResponseEntity<?> editarCultivo(@RequestBody Cultivo request) {
        System.out.println("Request recibido: " + request);

        if (request.getIdCultivo() == null) {
            return ResponseEntity.badRequest().body("ID es requerido para actualizar los datos");
        }

        try {
            cultivoService.editarCultivo(request);
            return ResponseEntity.ok().body("Datos actualizados correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar los datos: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idCultivo}")
    public ResponseEntity<String> eliminarCultivo(@PathVariable int idCultivo) {
        boolean eliminado = cultivoService.eliminarCultivo(idCultivo);
        if (eliminado) {
            return ResponseEntity.ok("Datos meteorológicos eliminados correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo eliminar los datos meteorológicos");
        }
    }

}
