package estacion.helvetas.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

}
