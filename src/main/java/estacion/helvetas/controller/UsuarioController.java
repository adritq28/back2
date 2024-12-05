
package estacion.helvetas.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import estacion.helvetas.model.Usuario;
import estacion.helvetas.repository.UsuarioRepository;
import estacion.helvetas.service.db.UsuarioServiceJpa;
import estacion.helvetas.services.IUsuarioService;

@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioServiceJpa usuarioService;

    @Autowired
    private IUsuarioService iusuarioService;

    @GetMapping(value = "/sol", produces = "application/json;charset=UTF-8")
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

    @GetMapping(value = "/verusuarios", produces = "application/json;charset=UTF-8")
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
            usuario.put("imagen", usuarioConEstacion[8]);
            usuariosConEstacion.add(usuario);
        }
        return usuariosConEstacion;
    }

    @PostMapping("/add")
    public ResponseEntity<String> guardarUsuario(@RequestBody Usuario usuario) {
        try {
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

    @GetMapping("/password/{idUsuario}")
    public String obtenerPassword(@PathVariable int idUsuario) {
        return usuarioService.obtenerPasswordIdUsuario(idUsuario);
    }

    @GetMapping("/sol/{id}")
    public ResponseEntity<Usuario> listarUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findByIdUsuario(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping(value = "/usuario-info", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Map<String, Object> responseData = Map.of(
                    "idUsuario", usuario.getIdUsuario(),
                    "nombre", usuario.getNombre(),
                    "apePat", usuario.getApePat(),
                    "apeMat", usuario.getApeMat(),
                    "ci", usuario.getCi(),
                    "telefono", usuario.getTelefono(),
                    "admin", usuario.isAdmin());
            return ResponseEntity.ok(responseData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

    @GetMapping("/perfil/{idUsuario}")
    public ResponseEntity<List<Map<String, Object>>> getPerfil(@PathVariable int idUsuario) {
        List<Object[]> perfilData = usuarioRepository.perfilObservador(idUsuario);

        // Transformar los resultados en un formato adecuado (lista de mapas)
        List<Map<String, Object>> resultado = perfilData.stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nombreCompleto", row[0]);
            map.put("telefono", row[1]);
            map.put("imagen", row[2]);
            // map.put("municipio", row[3]);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping(value = "/lista_usuario", produces = "application/json;charset=UTF-8")
    public List<Map<String, Object>> listaEstacionMteorologica() {
        List<Map<String, Object>> estacionHidrologica = new ArrayList<>();
        List<Object[]> listaestacionHidrologica = usuarioRepository.listaUsuario();
        for (Object[] usuarioConEstacion : listaestacionHidrologica) {
            // System.out.println("adawdansdasodaddasda");
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idUsuario", usuarioConEstacion[0]);
            usuario.put("nombre", usuarioConEstacion[1]);
            usuario.put("apePat", usuarioConEstacion[2]);
            usuario.put("apeMat", usuarioConEstacion[3]);
            usuario.put("ci", usuarioConEstacion[4]);
            usuario.put("admin", usuarioConEstacion[5]);
            usuario.put("telefono", usuarioConEstacion[6]);
            usuario.put("imagen", usuarioConEstacion[7]);
            usuario.put("rol", usuarioConEstacion[8]);
            usuario.put("estado", usuarioConEstacion[9]);
            usuario.put("password", usuarioConEstacion[10]);
            estacionHidrologica.add(usuario);
        }
        return estacionHidrologica;
    }

    @PostMapping("/editar")
    public ResponseEntity<?> editarUsuario(@RequestBody Usuario request) {
        if (request.getIdUsuario() == null) {
            return ResponseEntity.badRequest().body("ID es requerido para actualizar los datos");
        }
        usuarioService.editarUsuario(request);
        return ResponseEntity.ok().body("Datos actualizados correctamente");
    }

    @GetMapping("/roles/{idUsuario}")
    public ResponseEntity<?> obtenerDatosUsuario(@PathVariable Long idUsuario) {

        String rol = usuarioService.obtenerRolPorIdUsuario(idUsuario);
        List<Map<String, Object>> resultado = new ArrayList<>();

        switch (rol) {
            case "1":
                List<Object[]> datosAdmin = usuarioRepository.obtenerDatosAdministrador(idUsuario);
                resultado = mapearDatosAdmin(datosAdmin);
                break;
            case "2":
                List<Object[]> datosObservador = usuarioRepository.obtenerDatosObservador(idUsuario);
                resultado = mapearDatosObservador(datosObservador);
                break;
            case "3":
                List<Object[]> datosPromotor = usuarioRepository.obtenerDatosPromotor(idUsuario);
                resultado = mapearDatosPromotor(datosPromotor);
                break;
            default:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Rol no reconocido");
        }

        return ResponseEntity.ok(resultado);
    }

    private List<Map<String, Object>> mapearDatosAdmin(List<Object[]> datos) {
        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Object[] fila : datos) {
            Map<String, Object> map = new HashMap<>();
            map.put("idUsuario", fila[0]);
            map.put("nombre", fila[1]);
            map.put("apePat", fila[2]);
            map.put("apeMat", fila[3]);
            map.put("ci", fila[4]);
            map.put("telefono", fila[5]);
            map.put("usuarioImagen", fila[6]);
            map.put("rol", fila[7]);
            map.put("password", fila[8]);
            resultado.add(map);
        }
        return resultado;
    }

    // Método para mapear los datos de observador
    private List<Map<String, Object>> mapearDatosObservador(List<Object[]> datos) {
        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Object[] fila : datos) {
            Map<String, Object> map = new HashMap<>();
            map.put("municipio", fila[0]);
            map.put("estacion", fila[1]);
            map.put("idUsuario", fila[2]);
            map.put("nombre", fila[3]);
            map.put("apePat", fila[4]);
            map.put("apeMat", fila[5]);
            map.put("ci", fila[6]);
            map.put("admin", fila[7]);
            map.put("telefono", fila[8]);
            map.put("usuarioImagen", fila[9]);
            map.put("rol", fila[10]);
            map.put("tipoEstacion", fila[11]);
            map.put("idEstacion", fila[12]);
            map.put("idMunicipio", fila[13]);
            resultado.add(map);
        }
        return resultado;
    }

    // Método para mapear los datos de promotor
    private List<Map<String, Object>> mapearDatosPromotor(List<Object[]> datos) {
        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Object[] fila : datos) {
            Map<String, Object> map = new HashMap<>();
            map.put("municipio", fila[0]); // m.nombre
            map.put("zona", fila[1]); // z.nombre
            map.put("cultivoNombre", fila[2]); // c.nombre
            map.put("idUsuario", fila[3]); // u.idUsuario
            map.put("nombre", fila[4]); // u.nombre
            map.put("apePat", fila[5]); // u.apePat
            map.put("apeMat", fila[6]); // u.apeMat
            map.put("ci", fila[7]); // u.ci
            map.put("admin", fila[8]); // u.admin
            map.put("telefono", fila[9]); // u.telefono
            map.put("usuarioImagen", fila[10]); // u.imagen
            map.put("cultivoImagen", fila[11]); // c.imagen
            map.put("rol", fila[12]); // u.rol
            map.put("idZona", fila[13]);
            map.put("latitud", fila[14]);
            map.put("longitd", fila[15]);
            map.put("idMunicipio", fila[16]);
            map.put("tipoCultivo", fila[17]);
            map.put("fechaSiembra", fila[18]);
            map.put("idCultivo", fila[19]);
            map.put("edit", fila[20]);
            map.put("delete", fila[21]);
            resultado.add(map);
        }
        return resultado;
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int idUsuario) {
        boolean eliminado = usuarioService.eliminarUsuario(idUsuario);
        if (eliminado) {
            return ResponseEntity.ok("Datos meteorológicos eliminados correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo eliminar los datos meteorológicos");
        }
    }

    @PutMapping("/actualizarUltimoAcceso/{idUsuario}")
    public ResponseEntity<?> actualizarUltimoAcceso(@PathVariable Integer idUsuario) {
        Usuario usuario = iusuarioService.buscarPorIdUsuario(idUsuario);
        // System.out.print("aaaaaaaaasdasdsaa" + usuario.getUltimoAcceso());
        // Optional<Usuario> usuario = usuarioRepository.findByIdUsuario(idUsuario);
        if (usuario != null) {
            // usuario.setUltimoAcceso(LocalDateTime.now());
            usuario.setUltimoAcceso(new Date()); // Asigna la fecha y hora actuales
            // System.out.print("aaaaaaaaasdasdsaa" + usuario.getUltimoAcceso());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    // @PostMapping("/addUsuario")
    // public ResponseEntity<String> guanrdarUsuario(@RequestBody Usuario usuario) {
    // System.out.println("--------" + usuario.toString());
    // try {
    // System.out.println("--+++++--" + usuario.toString());
    // Usuario nuevoUsuario = usuarioRepository.save(usuario);
    // return ResponseEntity.status(HttpStatus.CREATED)
    // .body("usuario guardada con ID: " + nuevoUsuario.getIdUsuario());
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Error al guardar usuario: " + e.getMessage());
    // }
    // }

    @PostMapping("/addUsuario")
    public ResponseEntity<Map<String, Object>> addUsuario(
            @RequestParam("nombreUsuario") String nombreUsuario,
            @RequestParam("nombre") String nombre,
            @RequestParam("apePat") String apePat,
            @RequestParam("apeMat") String apeMat,
            @RequestParam("telefono") String telefono,
            @RequestParam("ci") String ci,
            @RequestParam("password") String password,
            @RequestParam("fechaCreacion") String fechaCreacion,
            @RequestParam("ultimoAcceso") String ultimoAcceso,
            @RequestParam("estado") Boolean estado,
            @RequestParam("rol") String rol,
            @RequestParam("delete") Boolean delete,
            @RequestParam("edit") Boolean edit,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {

        try {
            // Procesar la imagen si se envió
            String nombreImagen = null;
            if (imagen != null && !imagen.isEmpty()) {
                nombreImagen = UUID.randomUUID().toString() + ".png";
                String rutaImagen = "D:/ProyectoHelvetas/Frontend/HelvetasFrontv8/helvetasfront/images/" + nombreImagen;

                File directorio = new File("D:/ProyectoHelvetas/Frontend/HelvetasFrontv8/helvetasfront/images");
                if (!directorio.exists()) {
                    directorio.mkdirs();
                }

                File archivoImagen = new File(rutaImagen);
                imagen.transferTo(archivoImagen);
            }

            // Crear el nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombreUsuario(nombreUsuario);
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApePat(apePat);
            nuevoUsuario.setApeMat(apeMat);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setCi(ci);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setFechaCreacion(Timestamp.valueOf(LocalDateTime.parse(fechaCreacion)));
            nuevoUsuario.setUltimoAcceso(Timestamp.valueOf(LocalDateTime.parse(ultimoAcceso)));
            nuevoUsuario.setEstado(estado);
            nuevoUsuario.setRol(rol);
            nuevoUsuario.setDelete(delete);
            nuevoUsuario.setEdit(edit);
            nuevoUsuario.setImagen(nombreImagen);

            // Guardar el usuario en la base de datos
            Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

            // Preparar la respuesta como JSON con el idUsuario y un mensaje
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Usuario creado correctamente");
            response.put("idUsuario", usuarioGuardado.getIdUsuario()); // Usa el método adecuado para obtener el ID

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message", "Error al guardar la imagen"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message", "Error al crear usuario"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            // Ruta base donde están guardadas las imágenes
            String rutaBase = "D:/ProyectoHelvetas/Frontend/HelvetasFrontv8/helvetasfront/images/";

            // Cargar la imagen como un recurso
            Path rutaImagen = Paths.get(rutaBase).resolve(filename).normalize();
            Resource resource = new UrlResource(rutaImagen.toUri());

            if (!resource.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Devolver la imagen con la cabecera adecuada
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .cacheControl(CacheControl.noCache())
                    .body(resource);
        } catch (MalformedURLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar-contrasena")
    public ResponseEntity<String> actualizarContrasena(@RequestBody Map<String, Object> request) {
        // Obtener ID del usuario y la nueva contraseña desde el cuerpo de la solicitud
        int usuarioId = (int) request.get("usuarioId");
        String nuevaContrasena = (String) request.get("nuevaContrasena");

        boolean success = usuarioService.actualizarContrasena(usuarioId, nuevaContrasena);

        if (success) {
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la contraseña");
        }
    }

}
