
package estacion.helvetas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/ci/{idUsuario}")
    public String obtenerCi(@PathVariable int idUsuario) {
        return usuarioService.obtenerCiIdUsuario(idUsuario);
    }

    // @PostMapping("/validar")
    // public ResponseEntity<?> validarUsuario(@RequestBody Usuario request) {
    // Optional<Usuario> usuarioOptional =
    // usuarioService.findByIdUsuario(request.getIdUsuario());
    // // System.out.println("-------- " + usuarioOptional.get().getApeMat());
    // if (usuarioOptional.isPresent()) {
    // System.out.println("aaaaaaaaaaaa");
    // Usuario usuario = usuarioOptional.get();
    // if (usuario.getPassword().equals(request.getPassword()) && usuario.isAdmin())
    // {
    // return ResponseEntity.ok().build();
    // } else {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    // }

    // } else {
    // // Si no se encuentra el usuario, devolver Unauthorized
    // System.out.println("bbbbbbbbbbbb");
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    // }

    // }

    @GetMapping("/sol/{id}")
    public ResponseEntity<Usuario> listarUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findByIdUsuario(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CONTROL DE CALIDADAA
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String nombreUsuario = loginRequest.get("nombreUsuario");
        String password = loginRequest.get("password");

        Optional<Usuario> optionalUsuario = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            // Verifica la contraseña
            if (password.equals(usuario.getPassword())) {
                // Preparar los datos del usuario a devolver
                if (usuario.isAdmin()) {
                    // Construir el objeto JSON de respuesta
                    // Puedes personalizar los campos que deseas devolver
                    Map<String, Object> responseData = Map.of(
                            "idUsuario", usuario.getIdUsuario(),
                            "nombre", usuario.getNombre(),
                            "apePat", usuario.getApePat(),
                            "apeMat", usuario.getApeMat(),
                            "ci", usuario.getCi(),
                            "telefono", usuario.getTelefono());
                    return ResponseEntity.ok(responseData);
                } else {
                    return ResponseEntity.ok("El usuario no es administrador.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Contraseña incorrecta.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado en la base de datos.");
        }
    }

}