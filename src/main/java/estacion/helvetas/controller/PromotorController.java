package estacion.helvetas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.repository.PromotorRepository;
import estacion.helvetas.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/promotor")
// public class personaController {

@RestController
public class PromotorController {
    @Autowired
    private PromotorRepository promotorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/lista_promotor")
    public List<Map<String, Object>> buscaPromotor() {
        List<Map<String, Object>> usuarioPromotor = new ArrayList<>();
        List<Object[]> listausuarioPromotor = promotorRepository.buscarPromotores();
        for (Object[] usuarioConEstacion : listausuarioPromotor) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idUsuario", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);
            usuario.put("nombre", usuarioConEstacion[2]);
            usuario.put("nombreCompleto", usuarioConEstacion[3]);
            usuario.put("telefono", usuarioConEstacion[4]);
            usuario.put("idZona", usuarioConEstacion[5]);
            usuarioPromotor.add(usuario);
        }
        return usuarioPromotor;
    }

}
