package estacion.helvetas.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import estacion.helvetas.model.DatosEstacionDTO;
import estacion.helvetas.repository.DatosEstacionRepository;
import estacion.helvetas.repository.UsuarioRepository;
import estacion.helvetas.service.db.DatosEstacionServiceJpa;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/datosEstacion")
// public class personaController {

@RestController
public class DatosEstacionController {

    @Autowired
    private DatosEstacionRepository datosEstacionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // @Autowired
    // private DatosEstacionDTORepository datosEstacionRepository2;

    @Autowired
    private DatosEstacionServiceJpa estacionService;

    @Autowired
    private DatosEstacionServiceJpa serviceEstacion;

    @Autowired
    private DatosEstacionRepository datosRepository;

    @GetMapping("/verDatosEstacion")
    public List<Map<String, Object>> obtenerDatosEstacion() {

        List<Map<String, Object>> datosEst = new ArrayList<>();
        List<Object[]> listadatos = datosRepository.obtenerDatosEstacion();

        // List<Object[]> results = query.getResultList();
        // List<Map<String, Object>> datosEstacion = new ArrayList<>();

        for (Object[] result : listadatos) {
            Map<String, Object> datos = new HashMap<>();
            // datos.put("id", result[0]);
            datos.put("idUsuario", result[0]);
            datos.put("nombreMunicipio", result[1]);
            datos.put("nombreEstacion", result[2]);
            datos.put("tipoEstacion", result[3]);
            datos.put("nombreCompleto", result[4]);
            datos.put("fechaReg", result[5]);
            datos.put("tempMin", result[6]);
            datos.put("tempMax", result[7]);
            datosEst.add(datos);
        }

        return datosEst;
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<DatosEstacionDTO> obtenerDatosObservador(@PathVariable
    // int id) {
    // DatosEstacionDTO observador = observadorService.obtenerObservadorPorId(id);
    // DatosEstacionDTO observador = datosEstacionRepository.obtenerDatosEstacion().
    // if (observador != null) {
    // return ResponseEntity.ok(observador);
    // } else {
    // return ResponseEntity.notFound().build();
    // }
    // }

    @GetMapping("/{id}")
    public ResponseEntity<DatosEstacionDTO> obtenerDatosObservador(@PathVariable int id) {
        List<Object[]> resultados = datosEstacionRepository.obtenerDatosEstacion();

        // Buscar el observador con el ID proporcionado en los resultados
        for (Object[] resultado : resultados) {
            int idUsuario = (int) resultado[0];
            if (idUsuario == id) {
                // Construir el objeto DatosEstacionDTO utilizando los datos del resultado
                DatosEstacionDTO observador = new DatosEstacionDTO();
                observador.setIdUsuario(idUsuario);
                observador.setMunicipio((String) resultado[1]);
                observador.setEstacion((String) resultado[2]);
                observador.setTipoEstacion((String) resultado[3]);
                observador.setNombreCompleto((String) resultado[4]);
                observador.setFechaReg((Timestamp) resultado[5]);
                observador.setTempMin((Float) resultado[6]);
                observador.setTempMax((Float) resultado[7]);
                observador.setTempAmb((Float) resultado[8]);
                observador.setPcpn((Float) resultado[9]);
                observador.setTaevap((Float) resultado[10]);
                observador.setDirViento((String) resultado[11]);
                observador.setVelViento((Float) resultado[12]);
                // Asignar otros campos según sea necesario
                // ...

                return ResponseEntity.ok(observador);
            }
        }

        // Si no se encuentra el observador con el ID proporcionado, devolver 404 Not
        // Found
        return ResponseEntity.notFound().build();
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<DatosEstacion> actualizarDatosEstacion(@PathVariable("id") int id,
            @RequestBody DatosEstacion datosEstacionActualizado) {
        DatosEstacion datosEstacion = serviceEstacion.obtenerDatosEstacionPorId(id);
        if (datosEstacion != null) {
            // Actualizar los campos del objeto datosEstacion con los datos

            // en datosEstacionActualizado
            // System.out.println("++++++" + estacion.toString());
            datosEstacion.setTempMax(datosEstacionActualizado.getTempMax());
            datosEstacion.setTempMin(datosEstacionActualizado.getTempMin());
            datosEstacion.setTempAmb(datosEstacionActualizado.getTempAmb());
            datosEstacion.setPcpn(datosEstacionActualizado.getPcpn());
            datosEstacion.setTaevap(datosEstacionActualizado.getTaevap());
            datosEstacion.setFechaReg(datosEstacionActualizado.getFechaReg());
            datosEstacion.setDirViento(datosEstacionActualizado.getDirViento());
            datosEstacion.setVelViento(datosEstacionActualizado.getVelViento());
            datosEstacion.setIdEstacion(datosEstacionActualizado.getIdEstacion());

            // Guardar los cambios en la base de datos
            serviceEstacion.guardarDatosEstacion(datosEstacion);
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

                datosEstacion.setTempMax(datosEstacionActualizados.getTempMax());
                datosEstacion.setTempMin(datosEstacionActualizados.getTempMin());
                datosEstacion.setTempAmb(datosEstacionActualizados.getTempAmb());
                datosEstacion.setPcpn(datosEstacionActualizados.getPcpn());
                datosEstacion.setTaevap(datosEstacionActualizados.getTaevap());
                datosEstacion.setFechaReg(datosEstacionActualizados.getFechaReg());
                datosEstacion.setDirViento(datosEstacionActualizados.getDirViento());
                datosEstacion.setVelViento(datosEstacionActualizados.getVelViento());
                datosEstacion.setIdEstacion(datosEstacionActualizados.getIdEstacion());
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
