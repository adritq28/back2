package estacion.helvetas.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import estacion.helvetas.model.Municipio;
import estacion.helvetas.repository.MunicipioRepository;

@CrossOrigin(origins = "*")
@RequestMapping("/municipio")
@RestController
public class MunicipioController {

    @Autowired
    private MunicipioRepository municipioRepository;

    @GetMapping("/zona/{id}")
    public List<Map<String, Object>> buscaZonas(@PathVariable int id) {
        List<Map<String, Object>> municipioZona = new ArrayList<>();
        List<Object[]> listamunicipioZona = municipioRepository.obtenerZonasMunicipio(id);
        for (Object[] municipioConZona : listamunicipioZona) {
            Map<String, Object> municipio = new HashMap<>();
            municipio.put("idMunicipio", municipioConZona[0]);
            municipio.put("nombreMunicipio", municipioConZona[1]);
            municipio.put("idZona", municipioConZona[2]);
            municipio.put("nombreZona", municipioConZona[3]);
            municipio.put("nombreCultivo", municipioConZona[4]);
            municipio.put("idCultivo", municipioConZona[5]);
            municipio.put("latitud", municipioConZona[6]);
            municipio.put("longitud", municipioConZona[7]);
            municipio.put("nombreFechaSiembra", municipioConZona[8]);
            municipioZona.add(municipio);
        }
        return municipioZona;
    }

    @GetMapping("/zonaaa/{idMunicipio}")
    public List<Map<String, Object>> buscaZonasPorCultivo(@PathVariable int idMunicipio) {
        List<Map<String, Object>> municipioZona = new ArrayList<>();
        List<Object[]> listamunicipioZona = municipioRepository.obtenerZonaPorCultivo(idMunicipio);
        for (Object[] municipioConZona : listamunicipioZona) {
            Map<String, Object> municipio = new HashMap<>();
            municipio.put("nombreCultivo", municipioConZona[0]);
            municipio.put("nombreMunicipio", municipioConZona[1]);
            municipioZona.add(municipio);
        }
        return municipioZona;
    }

    @GetMapping("/cultivo/{idMunicipio}")
    public List<Map<String, Object>> buscaCultivoPorMunicipio(@PathVariable int idMunicipio) {
        List<Map<String, Object>> municipioZona = new ArrayList<>();
        List<Object[]> listamunicipioZona = municipioRepository.obtenerCultivoPorMunicipio(idMunicipio);
        for (Object[] municipioConZona : listamunicipioZona) {
            Map<String, Object> municipio = new HashMap<>();
            municipio.put("nombreMunicipio", municipioConZona[0]);
            municipio.put("nombreCultivo", municipioConZona[1]);
            municipioZona.add(municipio);
        }
        return municipioZona;
    }

    @GetMapping("/lista_municipio")
    public List<Map<String, Object>> listaEstaciones() {
        List<Map<String, Object>> estaciones = new ArrayList<>();
        List<Object[]> listaestacionHidrologica = municipioRepository.listaMunicipio();
        for (Object[] usuarioConEstacion : listaestacionHidrologica) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("nombreMunicipio", usuarioConEstacion[0]);
            usuario.put("idMunicipio", usuarioConEstacion[1]);
            estaciones.add(usuario);
        }
        return estaciones;
    }

    /////

    @PostMapping("/addMunicipio")
    public ResponseEntity<Map<String, Object>> addMunicipio(@RequestParam("nombre") String nombre,
            @RequestParam("fechaCreacion") String fechaCreacion,
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
            Municipio nuevoUsuario = new Municipio();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setFechaCreacion(Timestamp.valueOf(LocalDateTime.parse(fechaCreacion)));
            nuevoUsuario.setDelete(delete);
            nuevoUsuario.setEdit(edit);
            nuevoUsuario.setImagen(nombreImagen); // Asignar el nombre de la imagen

            // Guardar el usuario en la base de datos

            // Guardar el usuario en la base de datos
            Municipio municipioGuardado = municipioRepository.save(nuevoUsuario);

            // Preparar la respuesta como JSON con el idUsuario y un mensaje
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Municipio creado correctamente");
            response.put("idMunicipio", municipioGuardado.getIdMunicipio()); // Usa el método adecuado para obtener el
                                                                             // ID

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message", "Error al guardar la imagen"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message", "Error al crear Municipio"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
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

}
