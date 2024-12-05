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

import estacion.helvetas.model.Promotor;
import estacion.helvetas.repository.PromotorRepository;
import estacion.helvetas.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RequestMapping("/promotor")
@RestController

public class PromotorController {
    @Autowired
    private PromotorRepository promotorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/lista_promotor")
    public List<Map<String, Object>> buscaPromotor() {
        List<Map<String, Object>> usuarioPromotor = new ArrayList<>();
        List<Object[]> listausuarioPromotor = promotorRepository.buscarPromotoresJPQL();
        for (Object[] usuarioConEstacion : listausuarioPromotor) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idUsuario", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);
            usuario.put("nombreCompleto", usuarioConEstacion[2]);
            usuario.put("telefono", usuarioConEstacion[3]);
            usuario.put("idMunicipio", usuarioConEstacion[4]);
            usuario.put("imagen", usuarioConEstacion[5]);
            usuarioPromotor.add(usuario);
        }
        return usuarioPromotor;
    }

    @GetMapping("/lista_zonas/{id}")
    public List<Map<String, Object>> buscaPromotor(@PathVariable int id) {
        List<Map<String, Object>> usuarioPromotor = new ArrayList<>();
        List<Object[]> listausuarioPromotor = promotorRepository.buscarZonas(id);
        for (Object[] usuarioConEstacion : listausuarioPromotor) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idUsuario", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);
            usuario.put("nombreZona", usuarioConEstacion[2]);
            usuario.put("nombreCompleto", usuarioConEstacion[3]);
            usuario.put("idZona", usuarioConEstacion[4]);
            usuario.put("idCultivo", usuarioConEstacion[5]);
            usuario.put("nombreCultivo", usuarioConEstacion[6]);
            usuario.put("tipo", usuarioConEstacion[7]);
            usuario.put("imagen", usuarioConEstacion[8]);
            usuario.put("imagenP", usuarioConEstacion[9]);
            usuario.put("nombreFechaSiembra", usuarioConEstacion[10]);
            usuarioPromotor.add(usuario);
        }
        return usuarioPromotor;
    }

    @PostMapping("/addPromotor")
    public ResponseEntity<String> guardarObservador(@RequestBody Promotor promotor) {
        try {
            Promotor nuevoPromotor = promotorRepository.save(promotor);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Promotor guardado con ID: " + nuevoPromotor.getIdPromotor());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el promotor: " + e.getMessage());
        }
    }

}
