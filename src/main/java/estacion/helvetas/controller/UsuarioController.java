
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

import estacion.helvetas.model.Usuario;
import estacion.helvetas.repository.UsuarioRepository;
import estacion.helvetas.service.db.UsuarioServiceJpa;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/usuario")
// public class personaController {

@RestController
// @RequestMapping("/personas")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioServiceJpa usuarioService;

    // @GetMapping
    @GetMapping("/sol")
    public List<Usuario> listarUsuario() {

        List<Usuario> a = usuarioRepository.findAll();

        return usuarioRepository.findAll();
    }

    @GetMapping("/sol2")
    public List<Usuario> mostrarlistarUsuario() {
        // public String mostrarlistarPersonas() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        System.out.println("Lista de usuarios:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString());
        }
        return usuarios;
    }

    @GetMapping("/verusuarios")
    public List<Map<String, Object>> obtenerUsuariosConEstacion() {
        List<Map<String, Object>> usuariosConEstacion = new ArrayList<>();
        List<Object[]> listaUsuariosConEstacion = usuarioRepository.buscarUsuariosConEstacion();

        for (Object[] usuarioConEstacion : listaUsuariosConEstacion) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idUsuario", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);
            usuario.put("nombreEstacion", usuarioConEstacion[2]);
            usuario.put("tipoEstacion", usuarioConEstacion[3]);
            usuario.put("nombreCompleto", usuarioConEstacion[4]);
            usuario.put("telefono", usuarioConEstacion[5]);
            usuario.put("idEstacion", usuarioConEstacion[6]);
            usuario.put("codTipoEstacion", usuarioConEstacion[7]);
            usuariosConEstacion.add(usuario);
        }
        return usuariosConEstacion;
    }

    @PostMapping("/add")
    public ResponseEntity<String> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            // Usuario.setIdEstacion(100000);
            Usuario nuevaUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario guardada con ID: " + nuevaUsuario.getIdUsuario());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la usuario: " + e.getMessage());
        }
    }

    @GetMapping("/telefono/{idUsuario}")
    public String obtenerTelefono(@PathVariable int idUsuario) {
        return usuarioService.obtenerTelefonoPorIdUsuario(idUsuario);
    }
}