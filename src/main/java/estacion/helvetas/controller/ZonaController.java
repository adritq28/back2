package estacion.helvetas.controller;

import java.util.ArrayList;
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

import estacion.helvetas.model.Zona;
import estacion.helvetas.repository.ZonaRepository;
import estacion.helvetas.service.db.ZonaServiceJpa;

@CrossOrigin(origins = "*")
@RequestMapping("/zona")

@RestController
public class ZonaController {

    @Autowired
    private ZonaRepository zonaRepository;
    @Autowired
    private ZonaServiceJpa zonaServiceJpa;

    @PostMapping("/editar_zona")
    public ResponseEntity<?> editarZona(@RequestBody Zona request) {
        if (request.getIdZona() == null) {
            return ResponseEntity.badRequest().body("ID es requerido para actualizar los datos");
        }
        zonaServiceJpa.editarZona(request);
        return ResponseEntity.ok().body("Datos actualizados correctamente");
    }

    @GetMapping("/vermunicipios")
    public List<Map<String, Object>> obtenermunicipio() {
        List<Map<String, Object>> usuariosConEstacion = new ArrayList<>();
        List<Object[]> listaUsuariosConEstacion = zonaRepository.obtenerIdNombreMunicipios();
        for (Object[] usuarioConEstacion : listaUsuariosConEstacion) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idMunicipio", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);
            usuario.put("imagen", usuarioConEstacion[2]);
            usuariosConEstacion.add(usuario);
        }
        return usuariosConEstacion;
    }

    @GetMapping(value = "/lista_zonas", produces = "application/json;charset=UTF-8")
    public List<Map<String, Object>> listaZonas() {
        List<Map<String, Object>> estaciones = new ArrayList<>();
        List<Object[]> listaestacionHidrologica = zonaRepository.listaZonas();
        for (Object[] usuarioConEstacion : listaestacionHidrologica) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("nombreMunicipio", usuarioConEstacion[0]);
            usuario.put("nombreZona", usuarioConEstacion[1]);
            usuario.put("idMunicipio", usuarioConEstacion[2]);
            usuario.put("idZona", usuarioConEstacion[3]);

            estaciones.add(usuario);
        }
        return estaciones;
    }

    @GetMapping(value = "/lista_comunidad/{idZona}", produces = "application/json;charset=UTF-8")
    public List<Map<String, Object>> listaComunidades(@PathVariable int idZona) {
        List<Map<String, Object>> estaciones = new ArrayList<>();
        List<Object[]> listaestacionHidrologica = zonaRepository.listaComunidades(idZona);
        for (Object[] usuarioConEstacion : listaestacionHidrologica) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("nombreComunidad", usuarioConEstacion[0]);
            usuario.put("idZona", usuarioConEstacion[1]);

            estaciones.add(usuario);
        }
        return estaciones;
    }

    @PostMapping("/addZona")
    public ResponseEntity<Map<String, Object>> guardarZona(@RequestBody Zona zona) {
        try {
            Zona nuevaEstacion = zonaRepository.save(zona);

            // Crear una respuesta JSON
            Map<String, Object> response = new HashMap<>();
            response.put("idZona", nuevaEstacion.getIdZona());
            response.put("mensaje", "Zona guardada correctamente");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al guardar la zona: " + e.getMessage());
            return new ResponseEntity<>(Map.of("message", "Error al crear zona"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
