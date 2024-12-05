package estacion.helvetas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.DatosEstacionHidrologica;
import estacion.helvetas.model.DatosEstacionMeteorologica;
import estacion.helvetas.model.Estacion;
import estacion.helvetas.repository.EstacionRepository;
import estacion.helvetas.service.db.DatosEstacionHidrologicaServiceJpa;
import estacion.helvetas.service.db.DatosEstacionMeteorologicaServiceJpa;
import estacion.helvetas.service.db.EstacionServiceJpa;

@CrossOrigin(origins = "*")
@RequestMapping("/estacion")

@RestController
public class EstacionController {

    @Autowired
    private EstacionRepository estacionRepository;
    @Autowired
    private DatosEstacionMeteorologicaServiceJpa estacionService;
    @Autowired
    private DatosEstacionHidrologicaServiceJpa estacionHidroService;

    @Autowired
    private EstacionServiceJpa estacionServiceJpa;

    @GetMapping("/listaEstacion")
    public List<Estacion> listarEstacion() {
        List<Estacion> a = estacionRepository.findAll();
        return estacionRepository.findAll();
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

    @GetMapping("/verEstaciones/{id}")
    public List<Map<String, Object>> obtenermunicipio(@PathVariable int id) {
        List<Map<String, Object>> estacion = new ArrayList<>();
        List<Object[]> listaUsuariosConEstacion = estacionRepository.obtenerEstacion(id);
        for (Object[] usuarioConEstacion : listaUsuariosConEstacion) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idMunicipio", usuarioConEstacion[0]);
            usuario.put("nombre", usuarioConEstacion[1]);
            usuario.put("tipoEstacion", usuarioConEstacion[2]);
            usuario.put("codTipoEstacion", usuarioConEstacion[3]);
            usuario.put("idEstacion", usuarioConEstacion[4]);
            estacion.add(usuario);
        }
        return estacion;
    }

    @GetMapping("/lista_meteorologica")
    public List<Map<String, Object>> listaEstacionMteorologica() {
        List<Map<String, Object>> estacionHidrologica = new ArrayList<>();
        List<Object[]> listaestacionHidrologica = estacionRepository.listaEstacionMeteorogica();
        for (Object[] usuarioConEstacion : listaestacionHidrologica) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idEstacion", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);
            usuario.put("nombreEstacion", usuarioConEstacion[2]);
            usuario.put("tipoEstacion", usuarioConEstacion[3]);
            usuario.put("codTipoEstacion", usuarioConEstacion[4]);
            estacionHidrologica.add(usuario);
        }
        return estacionHidrologica;
    }

    @GetMapping("/lista_datos_meteorologica/{idEstacion}")
    public List<Map<String, Object>> listaDatosMeteorologica(@PathVariable int idEstacion) {
        List<Map<String, Object>> estacionMeteorologica = new ArrayList<>();
        List<Object[]> listaEstacionMeteorologica = estacionRepository.listaDatosMeteorologica(idEstacion);

        for (Object[] datos : listaEstacionMeteorologica) {
            Boolean delete = (Boolean) datos[9];
            if (delete == null || !delete) {
                Map<String, Object> registro = new HashMap<>();
                registro.put("tempMax", datos[0] != null ? datos[0] : null);
                registro.put("tempMin", datos[1] != null ? datos[1] : null);
                registro.put("pcpn", datos[2] != null ? datos[2] : null);
                registro.put("tempAmb", datos[3] != null ? datos[3] : null);
                registro.put("dirViento", datos[4] != null ? datos[4] : null);
                registro.put("velViento", datos[5] != null ? datos[5] : null);
                registro.put("taevap", datos[6] != null ? datos[6] : null);
                registro.put("fechaReg", datos[7] != null ? datos[7] : null);
                registro.put("idDatosEst", datos[8] != null ? datos[8] : null);
                registro.put("delete", delete);
                estacionMeteorologica.add(registro);
            }
        }

        return estacionMeteorologica;
    }

    @GetMapping("/nombre_observador/{idEstacion}")
    public String obtenerNombreObservador(@PathVariable int idEstacion) {
        List<Object[]> resultado = estacionRepository.obtNombreObservador(idEstacion);
        if (!resultado.isEmpty()) {
            return resultado.get(0)[0].toString();
        } else {
            return "Observador no encontrado";
        }
    }

    @PostMapping("/editar")
    public ResponseEntity<?> editarMeteorologica(@RequestBody DatosEstacionMeteorologica request) {
        if (request.getIdDatosEst() == null) {
            return ResponseEntity.badRequest().body("ID es requerido para actualizar los datos");
        }
        estacionService.editarMeteorologica(request);
        return ResponseEntity.ok().body("Datos actualizados correctamente");
    }

    @PostMapping("/editarHidrologica")
    public ResponseEntity<?> editarHidrologica(@RequestBody DatosEstacionHidrologica request) {
        if (request.getIdHidrologica() == null) {
            return ResponseEntity.badRequest().body("ID es requerido para actualizar los datos");
        }
        estacionHidroService.editarHidrologica(request);
        return ResponseEntity.ok().body("Datos actualizados correctamente");
    }

    @DeleteMapping("/eliminar/{idDatosEst}")
    public ResponseEntity<String> eliminarDatosEstacion(@PathVariable int idDatosEst) {
        boolean eliminado = estacionService.eliminarDatosEstacion(idDatosEst);
        if (eliminado) {
            return ResponseEntity.ok("Datos meteorológicos eliminados correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo eliminar los datos meteorológicos");
        }
    }

    @DeleteMapping("/eliminarH/{idHidrologica}")
    public ResponseEntity<String> eliminarHidroEstacion(@PathVariable int idHidrologica) {
        boolean eliminado = estacionHidroService.eliminarDatosHidrologicaEstacion(idHidrologica);
        if (eliminado) {
            return ResponseEntity.ok("Datos meteorológicos eliminados correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo eliminar los datos meteorológicos");
        }
    }

    @GetMapping("/lista_hidrologica")
    public List<Map<String, Object>> listaEstacionHidrologica() {
        List<Map<String, Object>> estacionHidrologica = new ArrayList<>();
        List<Object[]> listaestacionHidrologica = estacionRepository.listaEstacionHidrologica();
        for (Object[] usuarioConEstacion : listaestacionHidrologica) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idEstacion", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);
            usuario.put("nombreEstacion", usuarioConEstacion[2]);
            usuario.put("tipoEstacion", usuarioConEstacion[3]);
            usuario.put("codTipoEstacion", usuarioConEstacion[4]);
            estacionHidrologica.add(usuario);
        }
        return estacionHidrologica;
    }

    @GetMapping("/lista_datos_hidrologica/{idEstacion}")
    public List<Map<String, Object>> listaDatosHidrologica(@PathVariable int idEstacion) {
        List<Map<String, Object>> estacionMeteorologica = new ArrayList<>();
        List<Object[]> listaEstacionMeteorologica = estacionRepository.listaDatosHidrologica(idEstacion);
        for (Object[] datos : listaEstacionMeteorologica) {
            Boolean delete = (Boolean) datos[3];
            if (delete == null || !delete) {
                Map<String, Object> registro = new HashMap<>();
                registro.put("limnimetro", datos[0]);
                registro.put("fechaReg", datos[1]);
                registro.put("idHidrologica", datos[2]);
                registro.put("delete", delete);

                estacionMeteorologica.add(registro);
            }
        }

        return estacionMeteorologica;
    }

    @GetMapping(value = "/lista_estaciones", produces = "application/json;charset=UTF-8")
    public List<Map<String, Object>> listaEstaciones() {
        List<Map<String, Object>> estaciones = new ArrayList<>();
        List<Object[]> listaestacionHidrologica = estacionRepository.listaEstaciones();
        for (Object[] usuarioConEstacion : listaestacionHidrologica) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("nombreMunicipio", usuarioConEstacion[0]);
            usuario.put("nombreEstacion", usuarioConEstacion[1]);
            usuario.put("tipoEstacion", usuarioConEstacion[2]);
            usuario.put("idEstacion", usuarioConEstacion[3]);
            usuario.put("idMunicipio", usuarioConEstacion[4]);
            estaciones.add(usuario);
        }
        return estaciones;
    }

    @PostMapping("/editar_estacion")
    public ResponseEntity<?> editarEstacion(@RequestBody Estacion request) {
        if (request.getIdEstacion() == null) {
            return ResponseEntity.badRequest().body("ID es requerido para actualizar los datos");
        }
        estacionServiceJpa.editarEstacion(request);
        return ResponseEntity.ok().body("Datos actualizados correctamente");
    }

    @PostMapping("/addEstacion")
    public ResponseEntity<Map<String, Object>> guardarEstacion(@RequestBody Estacion estacion) {
        try {
            Estacion nuevaEstacion = estacionRepository.save(estacion);

            // Crear una respuesta JSON
            Map<String, Object> response = new HashMap<>();
            response.put("idEstacion", nuevaEstacion.getIdEstacion());
            response.put("mensaje", "Estación guardada correctamente");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al guardar la estación: " + e.getMessage());
            return new ResponseEntity<>(Map.of("message", "Error al crear usuario"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
