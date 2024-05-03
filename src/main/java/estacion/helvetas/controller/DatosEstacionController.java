package estacion.helvetas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.DatosEstacion;
import estacion.helvetas.repository.DatosEstacionRepository;
import estacion.helvetas.service.db.DatosEstacionServiceJpa;
import estacion.helvetas.services.IDatosEstacion;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/datosEstacion")
// public class personaController {

@RestController
public class DatosEstacionController {

    @Autowired
    private DatosEstacionRepository datosEstacionRepository;

    @Autowired
    private IDatosEstacion serviceEstacion;
    @Autowired
    private DatosEstacionServiceJpa estacionService;

    @Autowired
    private DatosEstacionServiceJpa serviceEstacion2;

    public List<DatosEstacion> mostrarlistarEstacion() {
        // public String mostrarlistarPersonas() {
        List<DatosEstacion> datosEstacions = datosEstacionRepository.findAll();

        // Imprimir la lista de personas
        System.out.println("Lista de Datos Estacion:");
        for (DatosEstacion datosEstacion : datosEstacions) {
            System.out.println(datosEstacion.toString());
        }

        return datosEstacions;
    }

    // @GetMapping
    @GetMapping("/listaDatosEstacion")
    public List<DatosEstacion> listarDatosEstacion() {

        List<DatosEstacion> a = datosEstacionRepository.findAll();
        // Pageable pageable = PageRequest.of(0, 10);
        // return datosEstacionRepository.findAll(pageable).getContent();
        return datosEstacionRepository.findAll();
    }

    @PostMapping("/addDatosEstacion")
    public ResponseEntity<String> guardarDatosEstacion(@RequestBody DatosEstacion datosEstacion) {
        System.out.println("--------" + datosEstacion.toString());
        try {
            // persona.setIdEstacion(100000);
            System.out.println("--+++++--" + datosEstacion.toString());
            DatosEstacion nuevodatosEstacion = datosEstacionRepository.save(datosEstacion);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("datos estacion guardada con ID: " + nuevodatosEstacion.getIdDatosEst());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la datos estacion: " + e.getMessage());
        }
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

    // @RequestMapping("/editar/{id}")
    // public ModelAndView editarDatosEstacion(@PathVariable("id") int idEstacion) {
    // System.out.println( "ENTRA AL MODEL");
    // ModelAndView model = new ModelAndView("estacion/editar");

    // DatosEstacion estacion = new DatosEstacion();
    // estacion = serviceEstacion.buscarPorIdDatosEstacion(idEstacion);
    // System.out.println("++++++" + estacion.toString());
    // model.addObject("estacion", estacion);
    // System.out.println("SALE DE MODEL");
    // return model;
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<DatosEstacion>
    // obtenerDatosEstacionPorId(@PathVariable("id") int id) {
    // DatosEstacion datosEstacion = serviceEstacion2.obtenerDatosEstacionPorId(id);
    // if (datosEstacion != null) {
    // return ResponseEntity.ok(datosEstacion);
    // } else {
    // return ResponseEntity.notFound().build();
    // }
    // }

    @PutMapping("/{id}")
    public ResponseEntity<DatosEstacion> actualizarDatosEstacion(@PathVariable("id") int id,
            @RequestBody DatosEstacion datosEstacionActualizado) {
        DatosEstacion datosEstacion = serviceEstacion2.obtenerDatosEstacionPorId(id);
        if (datosEstacion != null) {
            // Actualizar los campos del objeto datosEstacion con los datos proporcionados
            // en datosEstacionActualizado
            // System.out.println("++++++" + estacion.toString());
            datosEstacion.setCoordenada(datosEstacionActualizado.getCoordenada());
            datosEstacion.setDirVelViento(datosEstacionActualizado.getDirVelViento());
            datosEstacion.setFechaDatos(datosEstacionActualizado.getFechaDatos());
            datosEstacion.setPcpn(datosEstacionActualizado.getPcpn());
            datosEstacion.setTaevap(datosEstacionActualizado.getTaevap());
            datosEstacion.setTempAmb(datosEstacionActualizado.getTempAmb());
            datosEstacion.setTempMax(datosEstacionActualizado.getTempMax());
            datosEstacion.setTempMin(datosEstacionActualizado.getTempMin());

            // Guardar los cambios en la base de datos
            serviceEstacion2.guardarDatosEstacion(datosEstacion);
            return ResponseEntity.ok(datosEstacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    ////////////////////
    @PutMapping("/updateDatosEstacion/{id}")
    public ResponseEntity<String> actualizarDatosEstacion2(@PathVariable("id") int id,
            @RequestBody DatosEstacion datosEstacionActualizados) {
        try {
            // DatosEstacion datosEstacionExistente =
            // serviceEstacion2.obtenerDatosEstacionPorId(id);
            Optional<DatosEstacion> datosEstacionExistente2 = datosEstacionRepository.findById(id);
            if (datosEstacionExistente2 != null) {
                DatosEstacion datosEstacion = datosEstacionExistente2.get();
                // Actualiza los campos de la estación con los nuevos datos
                datosEstacion.setCoordenada(datosEstacionActualizados.getCoordenada());
                datosEstacion.setDirVelViento(datosEstacionActualizados.getDirVelViento());
                datosEstacion.setFechaDatos(datosEstacionActualizados.getFechaDatos());
                datosEstacion.setPcpn(datosEstacionActualizados.getPcpn());
                datosEstacion.setTaevap(datosEstacionActualizados.getTaevap());
                datosEstacion.setTempAmb(datosEstacionActualizados.getTempAmb());
                datosEstacion.setTempMax(datosEstacionActualizados.getTempMax());
                datosEstacion.setTempMin(datosEstacionActualizados.getTempMin());
                // Actualiza otros campos según sea necesario

                // Guarda la estación actualizada en la base de datos
                DatosEstacion datosEstacionActualizada = datosEstacionRepository.save(datosEstacion);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Datos de la estación actualizados con éxito. ID: "
                                + datosEstacionActualizada.getIdDatosEst());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la estación con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar los datos de la estación: " + e.getMessage());
        }
    }

}
