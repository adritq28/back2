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

import estacion.helvetas.model.DatosEstacionDTO;
import estacion.helvetas.model.DatosEstacionMeteorologica;
import estacion.helvetas.repository.DatosEstacionMeteorologicaRepository;
import estacion.helvetas.repository.MunicipioRepository;
import estacion.helvetas.repository.UsuarioRepository;
import estacion.helvetas.service.db.DatosEstacionMeteorologicaServiceJpa;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/datosEstacion")
// public class personaController {

@RestController
public class DatosEstacionMeteorologicaController {

    @Autowired
    private DatosEstacionMeteorologicaRepository datosEstacionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // @Autowired
    // private DatosEstacionDTORepository datosEstacionRepository2;

    @Autowired
    private DatosEstacionMeteorologicaServiceJpa estacionService;

    @Autowired
    private DatosEstacionMeteorologicaServiceJpa serviceEstacion;

    @Autowired
    private DatosEstacionMeteorologicaRepository datosRepository;
    @Autowired
    private MunicipioRepository muniRepository;

    @GetMapping("/{id}")
    public ResponseEntity<List<DatosEstacionDTO>> obtenerDatosObservador(@PathVariable int id) {
        List<Object[]> resultados = datosEstacionRepository.obtenerDatosEstacion(id);
        List<DatosEstacionDTO> observadores = new ArrayList<>();

        for (Object[] resultado : resultados) {
            int idUsuario = (int) resultado[0];
            if (idUsuario == id) {
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
                observador.setIdEstacion((int) resultado[13]);
                observador.setCodTipoEstacion((boolean) resultado[14]);
                observadores.add(observador);
            }
        }

        if (observadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(observadores);
    }

    @GetMapping("/obtener_estacion/{id}")
    public ResponseEntity<List<DatosEstacionDTO>> obtenerEstacion(@PathVariable int id) {
        List<Object[]> resultados = datosEstacionRepository.obtenerEstacion(id);
        List<DatosEstacionDTO> observadores = new ArrayList<>();
        for (Object[] resultado : resultados) {
            int idEstacion = (int) resultado[0];
            if (idEstacion == id) {
                DatosEstacionDTO observador = new DatosEstacionDTO();
                observador.setIdEstacion(idEstacion);
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
                observador.setIdUsuario((int) resultado[13]);
                observador.setCodTipoEstacion((boolean) resultado[14]);
                observadores.add(observador);
            }
        }

        if (observadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(observadores);
    }

    @GetMapping("/datos_municipio/{id}")
    public ResponseEntity<List<DatosEstacionDTO>> obtenerDatosMunicipio(@PathVariable int id) {
        List<Object[]> resultados = datosEstacionRepository.obtenerDatosMunicipio(id);
        List<DatosEstacionDTO> municipio = new ArrayList<>();

        for (Object[] resultado : resultados) {
            int idMunicipio = (int) resultado[0];
            if (idMunicipio == id) {
                DatosEstacionDTO observador = new DatosEstacionDTO();
                observador.setIdUsuario(idMunicipio);
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
                observador.setIdEstacion((int) resultado[13]);
                observador.setCodTipoEstacion((boolean) resultado[14]);
                municipio.add(observador);
            }
        }

        if (municipio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(municipio);
    }

    @GetMapping("/vermunicipios")
    public List<Map<String, Object>> obtenermunicipio() {
        List<Map<String, Object>> usuariosConEstacion = new ArrayList<>();
        List<Object[]> listaUsuariosConEstacion = muniRepository.obtenerIdNombreMunicipios();

        for (Object[] usuarioConEstacion : listaUsuariosConEstacion) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idMunicipio", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);

            usuariosConEstacion.add(usuario);
        }
        return usuariosConEstacion;
    }

    @GetMapping("/ver_estaciones")
    public List<Map<String, Object>> obtenerEstaciones() {
        List<Map<String, Object>> usuariosConEstacion = new ArrayList<>();
        List<Object[]> listaUsuariosConEstacion = muniRepository.obtenerIdNombreMunicipios();

        for (Object[] usuarioConEstacion : listaUsuariosConEstacion) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idMunicipio", usuarioConEstacion[0]);
            usuario.put("nombreMunicipio", usuarioConEstacion[1]);

            usuariosConEstacion.add(usuario);
        }
        return usuariosConEstacion;
    }

    public List<DatosEstacionMeteorologica> mostrarlistarEstacion() {
        // public String mostrarlistarPersonas() {
        List<DatosEstacionMeteorologica> datosEstacions = datosEstacionRepository.findAll();

        System.out.println("Lista de Datos Estacion:");
        for (DatosEstacionMeteorologica datosEstacion : datosEstacions) {
            System.out.println(datosEstacion.toString());
        }

        return datosEstacions;
    }

    @GetMapping("/listaDatosEstacion")
    public List<DatosEstacionMeteorologica> listarDatosEstacion() {

        List<DatosEstacionMeteorologica> a = datosEstacionRepository.findAll();
        // Pageable pageable = PageRequest.of(0, 10);
        // return datosEstacionRepository.findAll(pageable).getContent();
        return datosEstacionRepository.findAll();
    }

    @PostMapping("/addDatosEstacion")
    public ResponseEntity<String> guardarDatosEstacion(@RequestBody DatosEstacionMeteorologica datosEstacion) {
        System.out.println("--------" + datosEstacion.toString());
        try {
            // persona.setIdEstacion(100000);
            System.out.println("--+++++--" + datosEstacion.toString());
            DatosEstacionMeteorologica nuevodatosEstacion = datosEstacionRepository.save(datosEstacion);
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
    public ResponseEntity<DatosEstacionMeteorologica> actualizarDatosEstacion(@PathVariable("id") int id,
            @RequestBody DatosEstacionMeteorologica datosEstacionActualizado) {
        DatosEstacionMeteorologica datosEstacion = serviceEstacion.obtenerDatosEstacionPorId(id);
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
            @RequestBody DatosEstacionMeteorologica datosEstacionActualizados) {
        try {
            // DatosEstacion datosEstacionExistente =
            // serviceEstacion2.obtenerDatosEstacionPorId(id);
            Optional<DatosEstacionMeteorologica> datosEstacionExistente2 = datosEstacionRepository.findById(id);
            if (datosEstacionExistente2 != null) {
                DatosEstacionMeteorologica datosEstacion = datosEstacionExistente2.get();
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
                DatosEstacionMeteorologica datosEstacionActualizada = datosEstacionRepository.save(datosEstacion);
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
