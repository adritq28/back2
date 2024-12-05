package estacion.helvetas.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.model.Usuario;
import estacion.helvetas.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String nombreUsuario = loginRequest.get("nombreUsuario");
        String password = loginRequest.get("password");

        Optional<Usuario> optionalUsuario = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            if (password.equals(usuario.getPassword())) {
                if (usuario.isAdmin()) {
                    Map<String, Object> responseData = Map.of(
                            "idUsuario", usuario.getIdUsuario(),
                            "nombre", usuario.getNombre(),
                            "apePat", usuario.getApePat(),
                            "apeMat", usuario.getApeMat(),
                            "ci", usuario.getCi(),
                            "telefono", usuario.getTelefono(),
                            "imagen", usuario.getImagen());

                    return ResponseEntity.ok(responseData);
                } else {
                    return ResponseEntity.ok("El usuario no es administrador.");
                }
            } else {
                return ResponseEntity.status(401).body("Contraseña incorrecta.");
            }
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado en la base de datos.");
        }
    }
}

// package estacion.helvetas.controller;

// import estacion.helvetas.model.Usuario;
// import estacion.helvetas.repository.UsuarioRepository;
// import estacion.helvetas.service.RecaptchaService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Map;
// import java.util.Optional;

// @CrossOrigin(origins = "*")
// @RestController
// @RequestMapping("/api")
// public class LoginController {

// @Autowired
// private UsuarioRepository usuarioRepository;

// @Autowired
// private RecaptchaService recaptchaService; // Inyectar el servicio de
// reCAPTCHA

// @PostMapping("/login")
// public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest)
// {
// String nombreUsuario = loginRequest.get("nombreUsuario");
// String password = loginRequest.get("password");
// String recaptchaToken = loginRequest.get("recaptchaToken"); // Token
// reCAPTCHA

// // Verificar el token reCAPTCHA
// if (recaptchaToken == null ||
// !recaptchaService.verifyRecaptchaToken(recaptchaToken)) {
// return ResponseEntity.status(400).body("Token de reCAPTCHA inválido o no
// proporcionado.");
// }

// Optional<Usuario> optionalUsuario =
// usuarioRepository.findByNombreUsuario(nombreUsuario);

// if (optionalUsuario.isPresent()) {
// Usuario usuario = optionalUsuario.get();

// if (password.equals(usuario.getPassword())) {
// if (usuario.isAdmin()) {
// Map<String, Object> responseData = Map.of(
// "idUsuario", usuario.getIdUsuario(),
// "nombre", usuario.getNombre(),
// "apePat", usuario.getApePat(),
// "apeMat", usuario.getApeMat(),
// "ci", usuario.getCi(),
// "telefono", usuario.getTelefono(),
// "imagen", usuario.getImagen());

// return ResponseEntity.ok(responseData);
// } else {
// return ResponseEntity.ok("El usuario no es administrador.");
// }
// } else {
// return ResponseEntity.status(401).body("Contraseña incorrecta.");
// }
// } else {
// return ResponseEntity.status(404).body("Usuario no encontrado en la base de
// datos.");
// }
// }
// }
